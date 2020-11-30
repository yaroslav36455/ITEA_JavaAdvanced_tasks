package ua.itea;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import ua.itea.model.*;

public class POMParser extends DefaultHandler {	
	private Project project;
	private Stack<Object> stack;
	
	@Override
	public void startDocument() throws SAXException {
		stack = new Stack<>();
		stack.push(this);
	}
	
	@Override
	public void startElement(String uri, String localName, String qName,
							 Attributes attributes) throws SAXException {
		try {			
			Object currentObject = stack.top();
			Field currentObjectField = currentObject.getClass().getDeclaredField(convert(qName));
			Object fieldInstance = null;
			
			currentObjectField.setAccessible(true);
			if (currentObjectField.getType() == List.class) {
				if (currentObjectField.get(currentObject) == null) {
					currentObjectField.set(currentObject, new ArrayList<>());
				}
				ParameterizedType pt = (ParameterizedType) currentObjectField.getGenericType();
				Class<?> type = (Class<?>) pt.getActualTypeArguments()[0];
				ArrayList<?> list = (ArrayList<?>) currentObjectField.get(currentObject);
				Field arrField = list.getClass().getDeclaredField("elementData");
				
				list.add(null);
				arrField.setAccessible(true);
				Object[] arr = (Object[]) arrField.get(list);
				arrField.setAccessible(false);
				
				fieldInstance = type.newInstance();
				arr[list.size() - 1] = fieldInstance;
				
			} else {
				fieldInstance = currentObjectField.getType().newInstance();
				currentObjectField.set(currentObject, fieldInstance);
			}
			currentObjectField.setAccessible(false);
			
			setAttributes(fieldInstance, attributes);
			stack.push(fieldInstance);
		} catch (NoSuchFieldException | SecurityException
				| IllegalArgumentException | IllegalAccessException
				| InstantiationException e) {
			e.printStackTrace();
			throw new SAXException();
		}
	}
	
	private void setAttributes(Object fieldInstance, Attributes attributes)
													throws InstantiationException,
														   IllegalAccessException,
														   NoSuchFieldException,
														   SecurityException {
		Class<?> clazz = fieldInstance.getClass();
		if (clazz == Project.class) {
			Field attr = clazz.getDeclaredField("attributes");
			Class<?> newAttrClass = attr.getType();
			Object newAttrObj = newAttrClass.newInstance();
			
			for (int i = 0; i < attributes.getLength(); i++) {
				String argName = convert(attributes.getQName(i));
				Field field = newAttrClass.getDeclaredField(argName);
				
				field.setAccessible(true);
				field.set(newAttrObj, attributes.getValue(i));
				field.setAccessible(false);
			}
			
			attr.setAccessible(true);
			attr.set(fieldInstance, newAttrObj);
			attr.setAccessible(false);
		}
	}
	
	
	
	private String convert(String name) {
		
		switch (name) {
		case "project.build.sourceEncoding":
			return "projectBuildSourceEncoding";

		case "maven.compiler.source":
			return "mavenCompilerSource";
			
		case "maven.compiler.target":
			return "mavenCompilerTarget";
			
		case "xmlns:xsi":
			return "xmlnsXsi";
			
		case "xsi:schemaLocation":
			return "xsiSchemaLocation";
			
		default:
			return name;
		}
	}

	@Override
	public void endElement(String uri, String localName,
						   String qName) throws SAXException {
		stack.pop();
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		try {
			Object top = stack.top();
			
			if (top instanceof StringLeaf) {
				Field field = top.getClass().getSuperclass().getDeclaredField("value");
				String sub = new String(ch, start, length);
				
				field.setAccessible(true);
				field.set(stack.top(), sub);
				field.setAccessible(false);
			}
		} catch (IllegalArgumentException | IllegalAccessException
				| NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
			throw new SAXException();
		}
	}
	
	@Override
	public void endDocument() throws SAXException {
		stack.pop();
	}

	public Project getProject() {
		return project;
	}
}

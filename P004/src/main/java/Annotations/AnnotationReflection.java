package Annotations;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

public class AnnotationReflection {
	public Map<String, ArrayList<String>> getDecorators(Boolean tu) {
		AnnotationReflection annotationReflection = new AnnotationReflection();
		List<Object> allClasses = annotationReflection.findAllClassesUsingReflectionsLibrary("");
		Map<String, ArrayList<String>> multiValueMap = new HashMap<String, ArrayList<String>>();
		for (Object c : allClasses) {
			Method[] methods = ((Class<? extends AnnotationReflection>) c).getDeclaredMethods();
			if (tu) {
				RGDTUc annotation = ((Class<? extends AnnotationReflection>) c).getAnnotation(RGDTUc.class);
				if (annotation != null) {
					for (String entry : annotation.rules()) {
						entry = entry.trim();
						if (!multiValueMap.containsKey(entry)) {
							multiValueMap.put(entry, new ArrayList<String>());
						}
						String[] words = c.toString().split(" ");
						String[] classnames = words[words.length - 1].split("\\.");
						String[] classnames2 = classnames[classnames.length - 1].split("\\$");
						multiValueMap.get(entry).add(classnames2[classnames2.length - 1]);
					}
				}
				for (Method m : methods) {
					if (m.getAnnotation(RGDTUf.class) != null) {
						for (String entry : m.getAnnotation(RGDTUf.class).rules()) {
							entry = entry.trim();
							if (!multiValueMap.containsKey(entry)) {
								multiValueMap.put(entry, new ArrayList<String>());
							}
							String[] words = m.toString().split(" ");
							String[] functionnames = words[words.length - 1].split("\\.");
							multiValueMap.get(entry).add(functionnames[functionnames.length - 1]);
						}
					}
				}
			} else {
				RGDTIc annotation = ((Class<? extends AnnotationReflection>) c).getAnnotation(RGDTIc.class);
				if (annotation != null) {
					for (String entry : annotation.rules()) {
						entry = entry.trim();
						if (!multiValueMap.containsKey(entry)) {
							multiValueMap.put(entry, new ArrayList<String>());
						}
						String[] words = c.toString().split(" ");
						String[] classnames = words[words.length - 1].split("\\.");
						String[] classnames2 = classnames[classnames.length - 1].split("\\$");
						multiValueMap.get(entry).add(classnames2[classnames2.length - 1]);
					}
				}
				for (Method m : methods) {
					if (m.getAnnotation(RGDTIf.class) != null) {
						for (String entry : m.getAnnotation(RGDTIf.class).rules()) {
							entry = entry.trim();
							if (!multiValueMap.containsKey(entry)) {
								multiValueMap.put(entry, new ArrayList<String>());
							}
							String[] words = m.toString().split(" ");
							String[] functionnames = words[words.length - 1].split("\\.");
							multiValueMap.get(entry).add(functionnames[functionnames.length - 1]);

						}
					}
				}
			}

		}
		System.out.println(multiValueMap);
		return multiValueMap;

	}

	public List<Object> findAllClassesUsingReflectionsLibrary(String packageName) {
		Reflections reflections = new Reflections(packageName, new SubTypesScanner(false));
		return reflections.getSubTypesOf(Object.class).stream().collect(Collectors.toList());
	}
}
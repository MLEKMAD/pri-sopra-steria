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
    public Map<String, ArrayList<String>> getTiDecorators() {
    	AnnotationReflection hello=new AnnotationReflection();  
    	List<Object> setclass=hello.findAllClassesUsingReflectionsLibrary("");
    	Map<String, ArrayList<String>> multiValueMap = new HashMap<String, ArrayList<String>>();
    	for(Object s : setclass) {  
    		Method[] methods=((Class<? extends AnnotationReflection>) s).getDeclaredMethods();
    		RGDTIc annotation = ((Class<? extends AnnotationReflection>) s).getAnnotation(RGDTIc.class);
    		if(annotation != null) {
    			for(String text : annotation.text()) {
    				text = text.trim();
					if(multiValueMap.containsKey(text)) {
						String[] words = s.toString().split(" ");
						String[] classnames = words[words.length-1].split("\\.");
						String[] classnames2 = classnames[classnames.length-1].split("\\$");
						multiValueMap.get(text).add(classnames2[classnames2.length-1]);
					}else {
						multiValueMap.put(text, new ArrayList<String>());
						String[] words = s.toString().split(" ");
						String[] classnames = words[words.length-1].split("\\.");
						String[] classnames2 = classnames[classnames.length-1].split("\\$");
						multiValueMap.get(text).add(classnames2[classnames2.length-1]);
					}
    			}
    		}
    		for(Method m : methods) { 
    			if(m.getAnnotation(RGDTIf.class) != null) {
    				for(String entry : m.getAnnotation(RGDTIf.class).text()) {
    					entry = entry.trim();
    					if(multiValueMap.containsKey(entry)) {
    						String[] words = m.toString().split(" ");
    						String[] functionnames = words[words.length-1].split("\\.");
    						multiValueMap.get(entry).add(functionnames[functionnames.length-1]);
    					}else {
    						multiValueMap.put(entry, new ArrayList<String>());
    						String[] words = m.toString().split(" ");
    						String[] functionnames = words[words.length-1].split("\\.");
    						multiValueMap.get(entry).add(functionnames[functionnames.length-1]);
    					}
    				}		
    			}		
    		}
    	}
    	System.out.println(multiValueMap);
    	return multiValueMap;
		}
    public Map<String, ArrayList<String>> getTuDecorators() {
    	AnnotationReflection hello=new AnnotationReflection();  
    	List<Object> setclass=hello.findAllClassesUsingReflectionsLibrary("");
    	Map<String, ArrayList<String>> multiValueMap = new HashMap<String, ArrayList<String>>();
    	for(Object s : setclass) {  
    		Method[] methods=((Class<? extends AnnotationReflection>) s).getDeclaredMethods();
    		RGDTUc annotation = ((Class<? extends AnnotationReflection>) s).getAnnotation(RGDTUc.class);
    		if(annotation != null) {
    			for(String text : annotation.text()) {
    				text= text.trim();
					if(multiValueMap.containsKey(text)) {
						String[] words = s.toString().split(" ");
						String[] classnames = words[words.length-1].split("\\.");
						String[] classnames2 = classnames[classnames.length-1].split("\\$");
						multiValueMap.get(text).add(classnames2[classnames2.length-1]);
					}else {
						multiValueMap.put(text, new ArrayList<String>());
						String[] words = s.toString().split(" ");
						String[] classnames = words[words.length-1].split("\\.");
						String[] classnames2 = classnames[classnames.length-1].split("\\$");
						multiValueMap.get(text).add(classnames2[classnames2.length-1]);
					}
    			}
    		}
    		for(Method m : methods) { 
    			if(m.getAnnotation(RGDTUf.class) != null) {
    				for(String entry : m.getAnnotation(RGDTUf.class).text()) {
    					entry= entry.trim();
    					if(multiValueMap.containsKey(entry)) {
    						String[] words = m.toString().split(" ");
    						String[] functionnames = words[words.length-1].split("\\.");
    						multiValueMap.get(entry).add(functionnames[functionnames.length-1]);
    					}else {
    						multiValueMap.put(entry, new ArrayList<String>());
    						String[] words = m.toString().split(" ");
    						String[] functionnames = words[words.length-1].split("\\.");
    						multiValueMap.get(entry).add(functionnames[functionnames.length-1]);
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
        return reflections.getSubTypesOf(Object.class)
          .stream()
          .collect(Collectors.toList());
    }
    
    
@RGDTUf(text = {"TR106-R6", "BMW", "Ford", "Mazda","RG22"})
public void mafctTU() {
	
}

@RGDTUc(text = "TR106-R7 ")
class Class1 {
    public int num() {
        return 1;
    }
}
@RGDTIf(text = {"TR106-R6", "BMW", "Ford", "Mazda","RG22"})
public void mafctTI() {
	
}

@RGDTIc(text = "TR106-R7")
class Class2 {
    public int num() {
        return 1;
    }
}
}
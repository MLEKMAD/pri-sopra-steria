package Annotations;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

public class AnnotationReflection {
    public Map<String, ArrayList<String>> Annotation() {
    	AnnotationReflection hello=new AnnotationReflection();  
    	List<Object> setclass=hello.findAllClassesUsingReflectionsLibrary("");
    	Map<String, ArrayList<String>> multiValueMap = new HashMap<String, ArrayList<String>>();
    	/*multiValueMap.put("R25", new ArrayList<String>());
    	multiValueMap.put("RG22", new ArrayList<String>());
    	multiValueMap.put("Volvo", new ArrayList<String>());
    	multiValueMap.put("BMW", new ArrayList<String>());
    	multiValueMap.put("Volvo", new ArrayList<String>());
    	multiValueMap.put("Ford", new ArrayList<String>());
    	multiValueMap.put("Mazda", new ArrayList<String>());*/
    	for(Object s : setclass) {  
    		Method[] methods=((Class<? extends AnnotationReflection>) s).getDeclaredMethods();
    		RGDc annotation = ((Class<? extends AnnotationReflection>) s).getAnnotation(RGDc.class);
    		if(annotation != null) {
    			for(String text : annotation.text())
					if(multiValueMap.containsKey(text)) {
						String[] words = s.toString().split(" ");
						multiValueMap.get(text).add(words[words.length-1]);
					}else {
						multiValueMap.put(text, new ArrayList<String>());
						String[] words = s.toString().split(" ");
						multiValueMap.get(text).add(words[words.length-1]);
					}
    		}
    		for(Method m : methods) { 
    			if(m.getAnnotation(RGDf.class) != null) {
    				for(String entry : m.getAnnotation(RGDf.class).text()) {
    					if(multiValueMap.containsKey(entry)) {
    						String[] words = m.toString().split(" ");
    						multiValueMap.get(entry).add(words[words.length-1]);
    					}else {
    						multiValueMap.put(entry, new ArrayList<String>());
    						String[] words = m.toString().split(" ");
    						multiValueMap.get(entry).add(words[words.length-1]);
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
    
    
@RGDf(text = {"TR106-R6", "BMW", "Ford", "Mazda","RG22"})
public void mafct() {
	
}

@RGDc(text = "TR106-R7")
class Class1 {
    public int num() {
        return 1;
    }
}
}
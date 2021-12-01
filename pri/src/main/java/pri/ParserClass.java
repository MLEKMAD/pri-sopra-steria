package pri;

//import java.io.File;

//import java.io.FileNotFoundException;
//
//import com.github.javaparser.JavaParser;
//import com.github.javaparser.ParseResult;
//import com.github.javaparser.StaticJavaParser;
//import com.github.javaparser.ast.CompilationUnit;
//import com.github.javaparser.ast.expr.AssignExpr;
//import com.github.javaparser.ast.expr.MethodCallExpr;
//import com.github.javaparser.resolution.types.ResolvedType;
//import com.github.javaparser.symbolsolver.JavaSymbolSolver;
//import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
//import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
//import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;
//
//public class ParserClass {
//
////	private String getNewJavaFile(String newFileSource, String existingFileFullPath) throws FileNotFoundException {
////		JavaParser javaParser = new JavaParser();
////		ParseResult<CompilationUnit> newCompilationUnitParse = javaParser.parse(newFileSource);
////		CompilationUnit newCompilationUnit;
////		if (newCompilationUnitParse.isSuccessful() && newCompilationUnitParse.getResult().isPresent()) {
////			newCompilationUnit = newCompilationUnitParse.getResult().get();
////		} else {
////			System.out.println("Problem: " + newCompilationUnitParse.getProblem(0).toString());
////		}
////		return newFileSource;
////	}
//	private static final String FILE_PATH = "src/main/java/pri/SImpleClass.java";
//
//	public static void main(String[] args) throws FileNotFoundException {
//		TypeSolver typeSolver = new ReflectionTypeSolver();
//		JavaSymbolSolver symbolSolver = new JavaSymbolSolver(typeSolver);
//		StaticJavaParser.getStaticConfiguration().setSymbolResolver(symbolSolver);
//		CompilationUnit cu = StaticJavaParser.parse(new File(FILE_PATH));
//
////		cu.findAll(AssignExpr.class).forEach(ae -> {
////			 ResolvedType resolvedType = ae.calculateResolvedType();
////			 System.out.println(ae.toString() + " is a: " + resolvedType);
////			 });
//
////		System.out.println(" methods:");
////		 resolvedReferenceTypeDeclaration.getAllMethods().forEach(m ->
////		
////		System.out.println(String.format(" %s", m.getQualifiedSignature())));
////		 System.out.println();
//
//		cu.findAll(MethodCallExpr.class)
//				.forEach(mce -> System.out.println(mce.resolveInvokedMethod().getQualifiedSignature()));
//	}
//}

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Nest utility class.
 * 
 * @author audin
 *
 */
public class ParserClass {

	/**
	 * Get all methods of a class.
	 * 
	 * @param clazz The class.
	 * @return All methods of a class.
	 */
	public static Collection<String> getMethods(Class<?> clazz) {
		// if (log.isDebugEnabled()) {
		// log.debug("getMethods(Class<?>) - start");
		// }

		Collection<Method> found = new ArrayList<Method>();
		while (clazz != null) {
			for (Method m1 : clazz.getDeclaredMethods()) {
				boolean overridden = false;

				for (Method m2 : found) {
					if (m2.getName().equals(m1.getName())
							&& Arrays.deepEquals(m1.getParameterTypes(), m2.getParameterTypes())) {
						overridden = true;
						break;
					}
				}

				if (!overridden)
					found.add(m1);
			}

			clazz = clazz.getSuperclass();
		}
		Collection<String> methods = new ArrayList<String>();
		found.forEach(method -> {
			methods.add(method.getName());
		});

		// if (log.isDebugEnabled()) {
		// log.debug("getMethods(Class<?>) - end");
		// }
		return methods;
	}

	public static Collection<String> parseMethods(Collection<String> methods) {
		Collection<String> result = new ArrayList<String>();
		Pattern pattern = Pattern.compile("regGes*", Pattern.CASE_INSENSITIVE);
		methods.forEach(method -> {
			Matcher matcher = pattern.matcher(method);
			boolean matchFound = matcher.find();
			if (matchFound) {
				result.add(method);
			} 
		});

		return result;
	}

	public static void main(String[] args) {
		System.out.println(parseMethods(getMethods(SImpleClass.class)));
	}
}

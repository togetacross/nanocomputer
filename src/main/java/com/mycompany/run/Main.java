package com.mycompany.run;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.mycompany.model.ProcessorComponent;

@Configuration
@ComponentScan("com.mycompany")
public class Main {
	public static void main(String[] args) {
		
		/** Annotation */	
		ApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
		ProcessorComponent processor = (ProcessorComponent) context.getBean(ProcessorComponent.class);
		processor.run();
		
		
		/** XML */
		/*
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		ProcessorComponent processor = (ProcessorComponent) context.getBean("processorComponent");
		processor.run();
		*/
		
	}
}

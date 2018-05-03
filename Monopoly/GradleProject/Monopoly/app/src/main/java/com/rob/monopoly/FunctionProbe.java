package com.rob.monopoly;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Vector;

public class FunctionProbe {

    public Vector<Method> findMethods(Class myClass, Class	myAnno)
    {
        Vector<Method>	marked	=	new Vector<Method>();
        Method[]	methods	=	myClass.getMethods();
        for (Method	method	:	 methods)
        {
            Annotation anno=method.getAnnotation(myAnno);
            if(anno!=null)
            {
                marked.add(method);
            }
        }
        return marked;
    }


    public Object invokeMarked(Class myClass,Method method)
    {
        Object[] myArgs=null;
            try
            {
                if(Modifier.isStatic(method.getModifiers()))
                    return method.invoke(null,myArgs);
                else
                    return method.invoke(myClass.newInstance(),myArgs);
            }catch(Exception e)
            {
                e.printStackTrace();
            };
        return null;
    }

}

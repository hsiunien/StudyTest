package org.taptwo.android.widget;

import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/*public*/final class ViewFlowFix {
	private static final Method getMeasuredState;
	private static final Field mMeasuredWidth,mMeasuredHeight;
	static{
		getMeasuredState=getMethod(View.class, "getMeasuredState");
		if(getMeasuredState==null){
			mMeasuredWidth=getField(View.class,"mMeasuredWidth");
			mMeasuredHeight=getField(View.class,"mMeasuredHeight");
		}else{
			mMeasuredWidth=null;
			mMeasuredHeight=null;
		}
	}
	static final int getMeasuredState(View view){
		if(getMeasuredState==null){
			final int MEASURED_STATE_MASK = 0xff000000;
			final int MEASURED_HEIGHT_STATE_SHIFT = 16;
	        return (getIntFieldValue(view,mMeasuredWidth)&MEASURED_STATE_MASK)
	                | ((getIntFieldValue(view,mMeasuredHeight)>>MEASURED_HEIGHT_STATE_SHIFT)
	                        & (MEASURED_STATE_MASK>>MEASURED_HEIGHT_STATE_SHIFT));
		}
		return callMethod(getMeasuredState, view);
	}
	
	static final Field getField(Class clazz, String fieldName) {
		Field field = null;
		try {
			field = clazz.getDeclaredField(fieldName);
			if (field != null && !field.isAccessible()) {
				field.setAccessible(true);
			}
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
		return field;
	}
	static final int getIntFieldValue(Object obj, Field field) {
		try {
			return field.getInt(obj);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/* private */static int callMethod(Method method, Object obj) {
		if (method != null && obj != null) {
			try {
				Object ret = method.invoke(obj, null);
				if (ret != null && ret instanceof Integer) {
					((Integer) ret).intValue();
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	/* private */static final Method getMethod(Class clazz, String methodName) {
		Method method = null;
		try {
			method = clazz.getMethod(methodName, null);
			if (method != null && !method.isAccessible()) {
				method.setAccessible(true);
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return method;
	}
}

package org.proofs;

import java.util.*;
import org.objectweb.asm.*;


public class CreateClassHelloWorldASM implements Opcodes{



	public static byte[] dump () throws Exception {

	ClassWriter cw = new ClassWriter(0);
	FieldVisitor fv;
	MethodVisitor mv;
	AnnotationVisitor av0;

	cw.visit(V1_6, ACC_PUBLIC + ACC_SUPER, "org/proofs/HelloWorldASM", null, "java/lang/Object", null);

	{
	mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
	mv.visitCode();
	mv.visitVarInsn(ALOAD, 0);
	mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V");
	mv.visitInsn(RETURN);
	mv.visitMaxs(1, 1);
	mv.visitEnd();
	}
	{
	mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "hello", "()V", null, null);
	mv.visitCode();
	mv.visitLdcInsn(new Integer(43981));
	mv.visitVarInsn(ISTORE, 0);
	mv.visitLdcInsn(new Integer(43690));
	mv.visitVarInsn(ISTORE, 1);
	mv.visitVarInsn(ILOAD, 0);
	mv.visitVarInsn(ILOAD, 1);
	mv.visitInsn(ISUB);
	mv.visitVarInsn(ISTORE, 2);
	mv.visitVarInsn(ILOAD, 2);
	mv.visitMethodInsn(INVOKESTATIC, "java/lang/Integer", "toHexString", "(I)Ljava/lang/String;");
	mv.visitVarInsn(ASTORE, 3);
	mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
	mv.visitVarInsn(ALOAD, 3);
	mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V");
	mv.visitInsn(RETURN);
	mv.visitMaxs(2, 4);
	mv.visitEnd();
	}
	cw.visitEnd();

	return cw.toByteArray();
	}
}


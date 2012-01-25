package org.proofs;

import org.apache.bcel.generic.*;
import org.apache.bcel.classfile.*;
import org.apache.bcel.*;
import java.io.*;

public class CreateClassHelloWorldBCEL {
	  private InstructionFactory _factory;
	  private ConstantPoolGen    _cp;
	  private ClassGen           _cg;

	  public CreateClassHelloWorldBCEL() throws FileNotFoundException, IOException {
	    _cg = new ClassGen("org.proofs.HelloWorldBCEL", "java.lang.Object", "HelloWorldBCEL.java", Constants.ACC_PUBLIC | Constants.ACC_SUPER, new String[] {  });

	    _cp = _cg.getConstantPool();
	    _factory = new InstructionFactory(_cg, _cp);
	   create(new FileOutputStream("org/proofs/HelloWorldBCEL.class"));
	  
	  }

	  public void create(OutputStream out) throws IOException {
	    createMethod_0();
	    createMethod_1();
	    _cg.getJavaClass().dump(out);
	  }

	  private void createMethod_0() {
	    InstructionList il = new InstructionList();
	    MethodGen method = new MethodGen(Constants.ACC_PUBLIC, Type.VOID, Type.NO_ARGS, new String[] {  }, "<init>", "org.proofs.HelloWorldBCEL", il, _cp);

	    InstructionHandle ih_0 = il.append(_factory.createLoad(Type.OBJECT, 0));
	    il.append(_factory.createInvoke("java.lang.Object", "<init>", Type.VOID, Type.NO_ARGS, Constants.INVOKESPECIAL));
	    InstructionHandle ih_4 = il.append(_factory.createReturn(Type.VOID));
	    method.setMaxStack();
	    method.setMaxLocals();
	    _cg.addMethod(method.getMethod());
	    il.dispose();
	  }

	  private void createMethod_1() {
	    InstructionList il = new InstructionList();
	    MethodGen method = new MethodGen(Constants.ACC_PUBLIC | Constants.ACC_STATIC, Type.VOID, Type.NO_ARGS, new String[] {  }, "hello", "org.proofs.HelloWorldBCEL", il, _cp);

	    InstructionHandle ih_0 = il.append(new PUSH(_cp, 43981));
	    il.append(_factory.createStore(Type.INT, 0));
	    InstructionHandle ih_3 = il.append(new PUSH(_cp, 43690));
	    il.append(_factory.createStore(Type.INT, 1));
	    InstructionHandle ih_6 = il.append(_factory.createLoad(Type.INT, 0));
	    il.append(_factory.createLoad(Type.INT, 1));
	    il.append(InstructionConstants.ISUB);
	    il.append(_factory.createStore(Type.INT, 2));
	    InstructionHandle ih_10 = il.append(_factory.createLoad(Type.INT, 2));
	    il.append(_factory.createInvoke("java.lang.Integer", "toHexString", Type.STRING, new Type[] { Type.INT }, Constants.INVOKESTATIC));
	    il.append(_factory.createStore(Type.OBJECT, 3));
	    InstructionHandle ih_15 = il.append(_factory.createFieldAccess("java.lang.System", "out", new ObjectType("java.io.PrintStream"), Constants.GETSTATIC));
	    il.append(_factory.createLoad(Type.OBJECT, 3));
	    il.append(_factory.createInvoke("java.io.PrintStream", "println", Type.VOID, new Type[] { Type.STRING }, Constants.INVOKEVIRTUAL));
	    InstructionHandle ih_22 = il.append(_factory.createReturn(Type.VOID));
	    method.setMaxStack();
	    method.setMaxLocals();
	    _cg.addMethod(method.getMethod());
	    il.dispose();
	  }


}

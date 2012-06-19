package common.twitter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;

enum Type {
	String, List, Null
}

public class SerializationPractice {

	public static void main(String[] args) throws Exception{
		Object data = (Object) new Object[] {
				(Object) new Object[]{"112", "2343", "sdlfjsdio", 
					(Object) new Object[] {"sdfjsd"}
				},
				"data 1",
				"data 2",
				"data 3",
				"data 4",
				(Object) new Object[]{"112", "2343", "sdlfjsdio", 
						(Object) new Object[] {"sdfjsd"}
					},
		};
		
		byte[] serialized = serialize(data);
		
		System.out.println(serialized.length);
		System.out.println(Arrays.toString(serialized));
		
		Object obj = deserialize(serialized);
		
		System.out.println("done");
	}
	
	static byte[] serialize(Object o) throws IOException{
		
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(bos);
		
		serializeImpl(o, oos);
		
		oos.flush();
		return bos.toByteArray();
		
	}

	private static void serializeImpl(Object o, ObjectOutputStream oos) throws IOException {
		
		
		if(o == null){
			oos.writeObject(Type.Null);
		} else if(o instanceof Object[]){
			Object[] arr = (Object[]) o;
			oos.writeObject(Type.List);
			oos.writeInt(arr.length);
			for(Object obj : arr){
				serializeImpl(obj, oos);
			}
		} else {
			oos.writeObject(Type.String);
			byte[] encoded = o.toString().getBytes("utf-8");
			oos.writeInt(encoded.length);
			oos.write(encoded);
		}
		
	}
	
	static Object deserialize(byte[] byteArr) throws IOException, ClassNotFoundException{
		
		ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(byteArr));
		Object obj = deserializeImpl(ois);
		
		ois.close();
		return obj;
	}

	private static Object deserializeImpl(ObjectInputStream ois) throws ClassNotFoundException, IOException {
		
		Type t = (Type) ois.readObject();
		if(t == Type.Null){
			return null;
		} else if(t == Type.String){
			int len = ois.readInt();
			byte[] encoded = new byte[len];
			ois.read(encoded);
			return new String(encoded, "utf-8");
		} else {
			assert(t == Type.List);
			int len = ois.readInt();
			Object[] objArr = new Object[len];
			for(int i = 0; i < len; i ++){
				objArr[i] = deserializeImpl(ois);
			}
			
			return objArr;
		}
		
	}
}

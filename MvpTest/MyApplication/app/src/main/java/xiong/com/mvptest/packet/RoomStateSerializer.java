package xiong.com.mvptest.packet;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;

public class RoomStateSerializer implements JsonSerializer<TCPStruct.RoomState>, JsonDeserializer<TCPStruct.RoomState> {

	@Override
	public JsonElement serialize(TCPStruct.RoomState state, Type arg1,
                                 JsonSerializationContext arg2) {
		return new JsonPrimitive(state.ordinal());
	}

	// json转为对象时调用,实现JsonDeserializer<PackageState>接口  
    @Override
    public TCPStruct.RoomState deserialize(JsonElement json, Type typeOfT,
                                           JsonDeserializationContext context) throws JsonParseException {
        if (json.getAsInt() < TCPStruct.RoomState.values().length)
            return TCPStruct.RoomState.values()[json.getAsInt()];
        return null;  
    }  
}

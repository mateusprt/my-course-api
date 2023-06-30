package mycourses.mappers;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

public class Mapper {
	
	private static ModelMapper mapper = new ModelMapper();
	
	public static<O,D> D map(O origin, Class<D> destinationClass) {
		return mapper.map(origin, destinationClass);
	}
	
	public static<O,D> List<D> mapAll(List<O> originList, Class<D> destinationClass) {
		List<D> destinationList = new ArrayList<>();
		for(O item : originList) {
			destinationList.add(mapper.map(item, destinationClass));
		}
		return destinationList;
	}
	
}

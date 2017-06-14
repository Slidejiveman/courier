package courierdm;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import courierpd.enums.EmployeeRole;

@Converter
public class EmployeeConverter implements AttributeConverter<EmployeeRole, String>{

	@Override
	public String convertToDatabaseColumn(EmployeeRole role) {
		switch (role) {
			case Courier:
			    return "CO";
			case OrderTaker:
			    return "OT";
			case Owner:
				return "OW";
			case employee:
				return "CO";
			default:
				throw new IllegalArgumentException("Unknown " + role);
		}
	}
	
	@Override
	public EmployeeRole convertToEntityAttribute(String dbData) {
		switch (dbData) {
		    case "CO":
		    case "employee":
		    	return EmployeeRole.Courier;
		    case "OT":
		    	return EmployeeRole.OrderTaker;
		    case "OW":
		    	return EmployeeRole.Owner;
		    default:
		    	throw new IllegalArgumentException("Unknown " + dbData);
		    		
		}
	}
}

package com.mapway.database2java.model.schema;

// TODO: Auto-generated Javadoc
/**
 * The Class Function.
 */
public class Function {
	
	/** The Name. */
	private String Name;
	
	/** The arguments. */
	private Arguments arguments;
	
	/**
	 * Instantiates a new function.
	 */
	public Function()
	{
		Name="";
		arguments=new Arguments();
	}
	
		/**
		 * Gets the arguments.
		 *
		 * @return the arguments
		 */
		public Arguments getArguments() {
		return arguments;
	}

	/**
	 * Sets the arguments.
	 *
	 * @param arguments the new arguments
	 */
	public void setArguments(Arguments arguments) {
		this.arguments = arguments;
	}

		/**
		 * Gets the name.
		 *
		 * @return the name
		 */
		public String getName() {
		return Name;
	}
	
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		Name = name;
	}
}

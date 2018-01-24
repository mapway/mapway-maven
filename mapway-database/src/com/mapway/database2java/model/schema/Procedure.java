package com.mapway.database2java.model.schema;


// TODO: Auto-generated Javadoc
/**
 * The Class Procedure.
 */
public class Procedure {

	/** The Name. */
	private String Name;
	
	/** The arguments. */
	private Arguments arguments;
	
	/** The Package name. */
	private String PackageName;
	
	/**
	 * Instantiates a new procedure.
	 */
	public Procedure()
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

	/**
	 * Gets the package name.
	 *
	 * @return the package name
	 */
	public String getPackageName() {
		return PackageName;
	}

	/**
	 * Sets the package name.
	 *
	 * @param packageName the new package name
	 */
	public void setPackageName(String packageName) {
		PackageName = packageName;
	}
}

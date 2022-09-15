package equipe4.atlanticshippingmasters.model;


public enum Role {
	 USER(0, "USER"), ADMIN(1, "ADMIN");
	   
		private Integer code;
	    private String text;
	    Role(Integer code, String text) {
	    	this.code = code;
	        this.text = text;
		}
	    public Integer getCode() {
	        return code;
	    }

	    public void setCode(Integer code) {
	       this.code = code;
	    } 

	    public String getText() {
	        return text;
	    }

	    public void setText(String text) {
	       this.text = text;
	    }

  
}
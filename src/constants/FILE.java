package constants;

public interface FILE {
	
	String homeDir = System.getProperty("user.home");
	
	String FOLDER_DIR=homeDir+"/.ChangerThemeGTK/dir";
	String FOLDER_LIST=homeDir+"/.ChangerThemeGTK/list";
	
	
	String LIST_SOFT=FOLDER_LIST+"/list_soft.log";
	String LIST_SOFT_DIR=FOLDER_DIR+"/list_soft_dir.log";
	
	String LIST_THEMES=FOLDER_LIST+"/list_themes.log";
	String LIST_THEMES_DIR=FOLDER_DIR+"/list_themes_dir.log";
	
	
	String homeApplications=homeDir+"/.local/share/applications/";
}

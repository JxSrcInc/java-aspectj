package jxsource.maven.plugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

@Mojo( name = "echo")
public class EchoMojo extends AbstractMojo
{
	
    @Parameter(required = true /*defaultValue = "${project.build.directory}", property = "echo"*/ )
    private Message[] echo;

    public void execute() throws MojoExecutionException
    {
		getLog().info("number of echo messages: "+echo.length );
    	for(Message msg: echo) {
    		getLog().info( msg.getName()+" = "+msg.getValue() );
    	}
    }
}
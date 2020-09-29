If running from command line 

mkdir NewDir
cd NewDir 
git clone <thisrepo>
  
        TO obtain the Rollbar Access token follow these steps
        Login to Rollbar.com
        Click on settings
        click on  your project
        Project access tokens
        Copy the token next to where it says 'post_server_item'
         

export ROLLBAR_ACCESS_TOKEN=<post_server_item>; export ENVIRONMENT=<environment> export CODE_VERSION=<version>; mvn -X clean install compile test  

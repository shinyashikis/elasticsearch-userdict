# create empty project
mvn archetype:generate \
    -DarchetypeGroupId=org.codelibs \
    -DarchetypeArtifactId=elasticsearch-plugin-archetype \
    -DarchetypeVersion=6.6.0 \
    -DelasticsearchVersion=7.2.0 \
    -DgroupId=com.github.shinyashikis \
    -DartifactId=elasticsearch-userdict \
    -Dversion=1.0.0 \
    -DpluginName=Userdict \
    -DrestName=userdict

# maven -> eclipse project
mvn eclipse:eclipse


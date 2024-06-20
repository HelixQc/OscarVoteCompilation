package oscar.electionServices.persistence;

import jakarta.persistence.SharedCacheMode;
import jakarta.persistence.ValidationMode;
import jakarta.persistence.spi.ClassTransformer;
import jakarta.persistence.spi.PersistenceUnitInfo;
import jakarta.persistence.spi.PersistenceUnitTransactionType;

import javax.sql.DataSource;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class OscarPersistenceUnitInfo implements PersistenceUnitInfo {

    @Override
    public String getPersistenceUnitName() {
        return "OscarPersistenceUnitInfo";
    }

    @Override
    public String getPersistenceProviderClassName() {
        return "org.hibernate.jpa.HibernatePersistenceProvider";
    }

    @Override
    public List<String> getManagedClassNames() {
        List<String> managedClasses = new ArrayList<>();
        managedClasses.add("oscar.awardService.model.Award");
        managedClasses.add("oscar.awardService.model.Nomination");
        managedClasses.add("oscar.electionServices.model.Elector");
        managedClasses.add("oscar.electionServices.model.Vote");
        return managedClasses;
    }

    //I'm using alwaysData and a mariaDb database
    @Override
    public Properties getProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.format_sql", "false");
        properties.setProperty("hibernate.use_sql_comments", "false");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.ejb.interceptor", "org.hibernate.ejb.interceptor.EmptyInterceptor");
        properties.setProperty("javax.persistence.jdbc.driver", "org.mariadb.jdbc.Driver");
        properties.setProperty("javax.persistence.jdbc.url", "Put your URL database here!!!");
        properties.setProperty("javax.persistence.jdbc.user", "Your username!!!");
        properties.setProperty("javax.persistence.jdbc.password", "Your Password here");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MariaDBDialect");
        return properties;
    }


    @Override
    public PersistenceUnitTransactionType getTransactionType() {
        return null;
    }
    @Override
    public DataSource getJtaDataSource() {
        return null;
    }
    @Override
    public DataSource getNonJtaDataSource() {
        return null;
    }
    @Override
    public List<String> getMappingFileNames() {
        return null;
    }
    @Override
    public List<URL> getJarFileUrls() {
        return null;
    }
    @Override
    public URL getPersistenceUnitRootUrl() {
        return null;
    }
    @Override
    public boolean excludeUnlistedClasses() {
        return false;
    }
    @Override
    public SharedCacheMode getSharedCacheMode() {
        return null;
    }
    @Override
    public ValidationMode getValidationMode() {
        return null;
    }
    @Override
    public String getPersistenceXMLSchemaVersion() {
        return null;
    }
    @Override
    public ClassLoader getClassLoader() {
        return null;
    }
    @Override
    public void addTransformer(ClassTransformer classTransformer) {
    }
    @Override
    public ClassLoader getNewTempClassLoader() {
        return null;
    }
}

# mongo-jackson-codec
Mongo Codec using Jackson (and bson4jackson) for serialization

Usage
------
CodecRegistry codecRegistry = CodecRegistryFactory.getDefaultCodecRegistry(ObjectMapperFactory.createObjectMapper());

MongoClientOptions clientOptions = MongoClientOptions.builder()
            .clusterSettings(clusterSettings)
            .codecRegistry(codecRegistry)
            .build();

MongoClient client = MongoClients.create(clientOptions);


Mapping
--------
If you want to have ObjectId represented as String, annotate your field with @Id

Dependency.
------

Maven

    <dependency>
      <groupId>fr.javatic.mongo</groupId>
      <artifactId>mongo-jackson-codec</artifactId>
      <version>3.0.0-beta3__0.1</version>
      <scope>compile</scope>
    </dependency>

Gradle

    repositories {
        maven { url "http://dl.bintray.com/ylemoigne/maven" }
    }

    dependencies {
        compile 'fr.javatic.mongo:mongo-jackson-codec:3.0.0-beta3__0.1'
    }

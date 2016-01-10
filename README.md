# mongo-jackson-codec
Mongo Codec using Jackson (and bson4jackson) for serialization

Usage
------
    CodecRegistry codecRegistry = CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(),
                                                                 CodecRegistries.fromProviders(new JacksonCodecProvider(ObjectMapperFactory.createObjectMapper())));
    
    MongoClientOptions clientOptions = MongoClientOptions.builder()
                                                     .codecRegistry(codecRegistry)
                                                     .build();
    
    MongoClient client = new MongoClient(new ServerAddress(), clientOptions);
    
    MongoCollection<BlogPost> blogPosts = client.getDatabase("blog").getCollection("posts", BlogPost.class);
    
    BlogPost blogPost1 = new BlogPost("/first_blog",
                                  asList(new BlogPostComment(1, "First Comment"), new BlogPostComment(2, "Second Comment")));
    
    BlogPost blogPost2 = new BlogPost("/second_blog",
                                  asList(new BlogPostComment(2, "First Comment"), new BlogPostComment(2, "Second Comment")));
    
    blogPosts.insertMany(asList(blogPost1, blogPost2));
    
    List<BlogPost> allBlogPosts = blogPosts.find().into(new ArrayList<>()));


Mapping
--------
If you want to have ObjectId represented as String, annotate your field with @Id

Dependency.
------

Maven

    <dependency>
      <groupId>fr.javatic.mongo</groupId>
      <artifactId>mongo-jackson-codec</artifactId>
      <version>3.0.4__0.2</version>
      <scope>compile</scope>
    </dependency>

Gradle

    repositories {
        maven { url "http://dl.bintray.com/ylemoigne/maven" }
    }

    dependencies {
        compile 'fr.javatic.mongo:mongo-jackson-codec:3.0.4__0.2'
    }

Changelog
----------
0.1 Initial Release

0.2 Update to mongo 3.0.4 + test (thanks to https://github.com/mkmelin)

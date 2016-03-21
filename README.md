# mongo-jackson-codec
Mongo Codec using Jackson (and bson4jackson) for serialization

Usage
------
### Synchronous driver

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
    
    List<BlogPost> allBlogPosts = blogPosts.find().into(new ArrayList<>());

### Asynchronous driver

    MongoClient client = MongoClients.create();

    CodecRegistry codecRegistry = CodecRegistries.fromRegistries(
            MongoClients.getDefaultCodecRegistry(), CodecRegistries.fromProviders(
                    new ObjectCodecProvider(ObjectMapperFactory.createObjectMapper())));

    MongoDatabase database = client.getDatabase("blog").withCodecRegistry(codecRegistry);

    MongoCollection<BlogPost> blogPosts = database.getCollection("posts", BlogPost.class);

    BlogPost blogPost1 = new BlogPost("/first_blog",
            asList(new BlogPostComment(1, "First Comment"), new BlogPostComment(2, "Second Comment")));

    BlogPost blogPost2 = new BlogPost("/second_blog",
            asList(new BlogPostComment(2, "First Comment"), new BlogPostComment(2, "Second Comment")));

    blogPosts.insertMany(asList(blogPost1, blogPost2), new SingleResultCallback<Void>() {
        @Override
        public void onResult(Void aVoid, Throwable throwable) {

        }
    });

    blogPosts.find().into(new ArrayList<>(), new SingleResultCallback<ArrayList<BlogPost>>() {
        @Override
        public void onResult(ArrayList<BlogPost> blogPosts, Throwable throwable) {

        }
    });

Mapping
--------
If you want to have ObjectId represented as String, annotate your field with @Id

Dependency.
------

Maven

    <dependency>
      <groupId>fr.javatic.mongo</groupId>
      <artifactId>mongo-jackson-codec</artifactId>
      <version>3.2.0__0.3</version>
      <scope>compile</scope>
    </dependency>

Gradle

    repositories {
        maven { url "https://dl.bintray.com/ylemoigne/maven" }
    }

    dependencies {
        compile 'fr.javatic.mongo:mongo-jackson-codec:3.2.0__0.3'
    }

SBT

    resolvers += "ylemoigne" at "https://dl.bintray.com/ylemoigne/maven"

    libraryDependencies += "fr.javatic.mongo" % "mongo-jackson-codec" % "3.2.0__0.3"

Changelog
----------
0.1 Initial Release

0.2 Update to mongo 3.0.4 + test (thanks to https://github.com/mkmelin)

0.3 Update to mongo 3.2.0 + documentation improvement (thanks to [greenled](https://github.com/greenled))

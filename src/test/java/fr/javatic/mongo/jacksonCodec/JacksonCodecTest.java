/*
 * Copyright 2015 Jeff Yemin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package fr.javatic.mongo.jacksonCodec;

import fr.javatic.mongo.jacksonCodec.javaTime.JavaTimeBsonModule;
import org.bson.*;
import org.bson.codecs.BsonValueCodecProvider;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.codecs.configuration.CodecRegistries;
import org.junit.Test;

import java.time.OffsetDateTime;

import static org.junit.Assert.assertEquals;

public class JacksonCodecTest {

    private final JacksonCodec<BlogPost> codec = new JacksonCodec<>(
            ObjectMapperFactory.createObjectMapper().registerModule(new JavaTimeBsonModule()),
            CodecRegistries.fromProviders(new BsonValueCodecProvider()),
            BlogPost.class);

    private final OffsetDateTime testOffsetDateTime = OffsetDateTime.now();

    private static BsonDocument createBlogPostBson(String id, OffsetDateTime offsetDateTime){
        BsonDocument expectedDocument = new BsonDocument();
        expectedDocument.put("offsetDateTime", new BsonDateTime(offsetDateTime.toInstant().toEpochMilli()));
        expectedDocument.put("_id", new BsonString(id));

        return expectedDocument;
    }

    @Test
    public void testEncoderClass() {
        codec.getEncoderClass().equals(BlogPost.class);
    }

    @Test
    public void testEncoding() {
        BsonDocumentWriter writer = new BsonDocumentWriter(new BsonDocument());

        BlogPost blogPost = new BlogPost();
        blogPost.setId("/first_blog");
        blogPost.setOffsetDateTime(testOffsetDateTime);

        codec.encode(writer, blogPost, EncoderContext.builder().build());

        assertEquals(createBlogPostBson(blogPost.getId(), blogPost.getOffsetDateTime()), writer.getDocument());
    }

    @Test
    public void testDecoding() {
        String idValue = "/first_blog";
        BsonDocumentReader reader = new BsonDocumentReader(createBlogPostBson(idValue, testOffsetDateTime));

        BlogPost blogPost = codec.decode(reader, DecoderContext.builder().build());

        BlogPost expectedBlogPost = new BlogPost();
        expectedBlogPost.setId(idValue);
        expectedBlogPost.setOffsetDateTime(testOffsetDateTime);

        assertEquals(expectedBlogPost, blogPost);
        assertEquals(testOffsetDateTime.toInstant(), blogPost.getOffsetDateTime().toInstant());
    }


}

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

import org.bson.BsonDocument;
import org.bson.BsonDocumentReader;
import org.bson.BsonDocumentWriter;
import org.bson.BsonString;
import org.bson.codecs.BsonDocumentCodec;
import org.bson.codecs.BsonValueCodecProvider;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.codecs.RawBsonDocumentCodec;
import org.bson.codecs.configuration.CodecRegistries;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JacksonCodecTest {

    private final JacksonCodec<BlogPost> codec = new JacksonCodec<>(ObjectMapperFactory.createObjectMapper(),
                                                                    CodecRegistries.fromProviders(new BsonValueCodecProvider()),
                                                                    BlogPost.class);

    @Test
    public void testEncoderClass() {
        codec.getEncoderClass().equals(BlogPost.class);
    }

    @Test
    public void testEncoding() {
        BsonDocumentWriter writer = new BsonDocumentWriter(new BsonDocument());

        BlogPost blogPost = new BlogPost();
        blogPost.setId("/first_blog");

        codec.encode(writer, blogPost, EncoderContext.builder().build());

        assertEquals(new BsonDocument("_id", new BsonString(blogPost.getId())), writer.getDocument());
    }

    @Test
    public void testDecoding() {
        String idValue = "/first_blog";
        BsonDocumentReader reader = new BsonDocumentReader(new BsonDocument("_id", new BsonString(idValue)));

        BlogPost blogPost = codec.decode(reader, DecoderContext.builder().build());

        BlogPost expectedBlogPost = new BlogPost();
        expectedBlogPost.setId(idValue);

        assertEquals(expectedBlogPost, blogPost);
    }
}

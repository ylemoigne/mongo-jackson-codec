/*
 * Copyright 2015 Yann Le Moigne
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

import com.fasterxml.jackson.databind.ObjectMapper;
import org.bson.codecs.BsonValueCodecProvider;
import org.bson.codecs.ValueCodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.configuration.CodecRegistries;

import java.util.Arrays;

public class CodecRegistryFactory {
    private CodecRegistryFactory() {
    }

    public static CodecRegistry getDefaultCodecRegistry(ObjectMapper bsonObjectMapper) {
        return CodecRegistries.fromProviders(Arrays.asList(
            new ValueCodecProvider(),
            new BsonValueCodecProvider(),
            new ObjectCodecProvider(bsonObjectMapper)
        ));
    }
}

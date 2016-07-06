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

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;

import java.time.OffsetDateTime;

public class BlogPost {
    private String id;
    private OffsetDateTime offsetDateTime;

    @JsonProperty("_id")
    public String getId() {
        return id;
    }
    @JsonProperty("_id")
    public void setId(String id) {
        this.id = id;
    }


    public OffsetDateTime getOffsetDateTime() {return offsetDateTime;}
    public void setOffsetDateTime(OffsetDateTime offsetDateTime) {this.offsetDateTime = offsetDateTime;}

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BlogPost blogPost = (BlogPost) o;

        if (id != null ? !id.equals(blogPost.id) : blogPost.id != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}

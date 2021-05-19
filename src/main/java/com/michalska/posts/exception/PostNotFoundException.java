package com.michalska.posts;

import java.text.MessageFormat;

public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException(int id) {
        super(MessageFormat.format("Could not find post with id: {0}", id));
    }
}

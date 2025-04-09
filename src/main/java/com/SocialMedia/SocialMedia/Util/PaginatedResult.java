package com.SocialMedia.SocialMedia.Util;



import lombok.Data;

import java.util.List;

@Data
public class PaginatedResult<T> {
        private List<T> items;
        private String lastEvaluatedKey;

        public PaginatedResult(List<T> items, String lastEvaluatedKey) {
            this.items = items;
            this.lastEvaluatedKey = lastEvaluatedKey;
        }


    }



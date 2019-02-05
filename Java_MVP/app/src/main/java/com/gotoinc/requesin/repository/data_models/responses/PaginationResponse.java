package com.gotoinc.requesin.repository.data_models.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Illia Derevianko on 23.11.18.
 * GoTo Inc.
 */
public class PaginationResponse<T> {
    @Expose(serialize = false)
    @SerializedName("page")
    private int page;
    @Expose(serialize = false)
    @SerializedName("per_page")
    private int perPage;
    @Expose(serialize = false)
    @SerializedName("total")
    private int total;
    @Expose(serialize = false)
    @SerializedName("total_pages")
    private int totalPages;
    @Expose(serialize = false)
    @SerializedName(value = "data")
    private T data;

    public int getPage() {
        return page;
    }

    public int getPerPage() {
        return perPage;
    }

    public int getTotal() {
        return total;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public T getData() {
        return data;
    }

    @Override
    public String toString() {
        return "PaginationResponse{" +
                "page=" + page +
                ", perPage=" + perPage +
                ", total=" + total +
                ", totalPages=" + totalPages +
                '}';
    }
}

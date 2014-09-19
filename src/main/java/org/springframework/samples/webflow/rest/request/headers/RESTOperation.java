package org.springframework.samples.webflow.rest.request.headers;

import com.google.gson.annotations.SerializedName;

/**
 * Created by orcwarrior on 2014-09-15.
 */
public enum RESTOperation {
    /* REQUESTS */
     /* authorization */
    @SerializedName("RESTOperation.login")
    login,
    @SerializedName("RESTOperation.logout")
    logout,
    @SerializedName("RESTOperation.register")
    register,
    @SerializedName("RESTOperation.getRoles")
    getRoles,
    /* article basic */
    @SerializedName("RESTOperation.getArticles")
    getArticles,
    @SerializedName("RESTOperation.getArticles")
    getArticleFull,
    @SerializedName("RESTOperation.getArticles")
    getArticleComments,
    @SerializedName("RESTOperation.getArticles")
    favoriteArticle,
    /* article modifications */
    @SerializedName("RESTOperation.getArticles")
    editArticle,
    @SerializedName("RESTOperation.getArticles")
    deleteArticle,
    @SerializedName("RESTOperation.getArticles")
    createArticle,
    /* comments */
    @SerializedName("RESTOperation.getArticles")
    addComment,
    @SerializedName("RESTOperation.getArticles")
    deleteComment

}

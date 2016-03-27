package cosmicfeatureturtle.data

import scalikejdbc._
import cosmicfeatureturtle.models.Models._

object Queries {
  def createUser(createUserRequest: CreateUserRequest) = {
    sql"""
      insert into user
        (name, password, api_key)
      values
        (${createUserRequest.username.toLowerCase}, sha2(${createUserRequest.password}, 224), uuid())"""
  }

  def retrieveCreatedUser(createUserRequest: CreateUserRequest) = {
    sql"""
      select
        id_user,
        name,
        api_key
      from user
      where name = ${createUserRequest.username.toLowerCase}"""
  }

  def loginUser(loginUserRequest: LoginUserRequest) = {
    sql"""
      select
        id_user,
        name,
        api_key
      from user
      where name = ${loginUserRequest.username.toLowerCase}
        and password = sha2(${loginUserRequest.password}, 224)"""
  }

  def validateCredentialRequest(credentialRequest: CredentialRequest) = {
    sql"""
      select
        id_user,
        name
      from user
      where id_user = ${credentialRequest.idUser}
        and api_key = ${credentialRequest.apiKey}"""
  }

  def createFeature(createFeatureRequest: CreateFeatureRequest) = {
    sql"""
      insert into feature
        (id_user, title, idea)
      values
        (${createFeatureRequest.idUser}, ${createFeatureRequest.title}, ${createFeatureRequest.idea})"""
  }

  def retrieveCreatedFeature(createFeatureRequest: CreateFeatureRequest) = {
    sql"""
      select
        id_feature,
        title,
        idea
      from feature
      where id_user = ${createFeatureRequest.idUser}
        and title = ${createFeatureRequest.title}
        and idea = ${createFeatureRequest.idea}"""
  }

  def deleteFeature(deleteFeatureRequest: DeleteFeatureRequest) = {
    sql"""
      update feature set
        id_user = null,
        title = null,
        idea = null,
        date_edited = now()
      where id_user = ${deleteFeatureRequest.idUser}
        and id_feature = ${deleteFeatureRequest.idFeature}"""
  }

  def editFeature(editFeatureRequest: EditFeatureRequest) = {
    sql"""
      update feature set
        title = ${editFeatureRequest.title},
        idea = ${editFeatureRequest.idea},
        date_edited = now()
      where id_user = ${editFeatureRequest.idUser}
        and id_feature = ${editFeatureRequest.idFeature}"""
  }

  def getFeatureSummaries(limit: Int, skip: Int) = {
    sql"""
      select
        feature.id_user,
        user.name,
        feature.id_feature,
        feature.title,
        feature.idea,
        comment.comment_count,
        vote.upvote_count,
        vote.downvote_count
      from feature
        left join (
          select
            id_user,
            name
          from user
        ) user on feature.id_user = user.id_user
        left join (
          select
            id_feature,
            count(id_comment) as comment_count
          from comment
          group by id_feature
        ) comment on feature.id_feature = comment.id_feature
        left join (
          select
            id_feature,
            sum(if(upvote = 1, 1, 0)) as upvote_count,
            sum(if(upvote = 0, 1, 0)) as downvote_count
          from vote
          group by id_feature
        ) vote on feature.id_feature = vote.id_feature
      limit $limit offset $skip"""
  }

  def getFeatureById(idFeature: Int) = {
    sql"""
      select
        feature.id_user,
        user.name,
        feature.id_feature,
        feature.title,
        feature.idea
      from feature
      join user on feature.id_user = user.id_user
      where id_feature = $idFeature"""
  }

  def getFeatureCommentsById(idFeature: Int) = {
    sql"""
      select
        comment.id_user,
        user.name,
        comment.id_feature,
        comment.id_comment,
        comment.response
      from comment
      join user on comment.id_user = user.id_user
      where comment.id_feature = $idFeature"""
  }

  def getFeatureVotesById(idFeature: Int) = {
    sql"""
      select
        vote.id_user,
        user.name,
        vote.id_feature,
        vote.id_vote,
        vote.upvote
      from vote
      join user on vote.id_user = user.id_user
      where vote.id_feature = $idFeature"""
  }

  def createVote(createVoteRequest: CreateVoteRequest) = {
    sql"""
      insert into vote
        (id_user, id_feature, upvote)
      values
        (${createVoteRequest.idUser}, ${createVoteRequest.idFeature}, ${createVoteRequest.upvote})"""
  }

  def retrieveCreatedVote(createVoteRequest: CreateVoteRequest) = {
    sql"""
      select
        id_vote
      from vote
      where id_user = ${createVoteRequest.idUser}
        and id_feature = ${createVoteRequest.idFeature}"""
  }

  def deleteVote(deleteVoteRequest: DeleteVoteRequest) = {
    sql"""
      delete from vote
      where id_vote = ${deleteVoteRequest.idVote}"""
  }

  def editVote(editVoteRequest: EditVoteRequest) = {
    sql"""
      update vote set
        upvote = ${editVoteRequest.upvote}
      where id_vote = ${editVoteRequest.idVote}"""
  }
}

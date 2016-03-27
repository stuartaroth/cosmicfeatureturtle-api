package cosmicfeatureturtle.data

import scalikejdbc.{AutoSession, ConnectionPool}
import cosmicfeatureturtle.models.Models._
import cosmicfeatureturtle.models.DBResultExtensions._

object Execute {
  Class.forName("com.mysql.jdbc.Driver")
  ConnectionPool.singleton("jdbc:mysql://localhost/cosmicfeatureturtle", "root", "")
  implicit val session = AutoSession

  def createUser(createUserRequest: CreateUserRequest): CreateUserResponse = {
    if(createUserRequest.password == createUserRequest.repassword) {
      val result = Queries.createUser(createUserRequest).update.apply
      if(result == 1) {
        val users = Queries.retrieveCreatedUser(createUserRequest).map(_.toCreateUserResponse).list.apply
        if(users.nonEmpty) users.head else throw new CosmicFeatureTurtleException("There was a problem retrieving your user info.")
      }
      else
        throw new CosmicFeatureTurtleException
    } else {
      throw new CosmicFeatureTurtleException("Passwords do not match")
    }
  }

  def loginUser(loginUserRequest: LoginUserRequest): LoginUserResponse = {
    val users = Queries.loginUser(loginUserRequest).map(_.toLoginUserResponse).list.apply
    if(users.nonEmpty) users.head else throw new CosmicFeatureTurtleException("Username and password do not match.")
  }

  def validateCredentialRequest(credentialRequest: CredentialRequest) = {
    val users = Queries.validateCredentialRequest(credentialRequest).map(_.toCredentialResponse).list.apply
    if(users.isEmpty || users.head.idUser != credentialRequest.idUser)
      throw new CosmicFeatureTurtleException("Username and key do not match.")
  }

  def createFeature(createFeatureRequest: CreateFeatureRequest): CreateFeatureResponse = {
    validateCredentialRequest(createFeatureRequest)
    val result = Queries.createFeature(createFeatureRequest).update.apply
    if(result == 1) {
      val features = Queries.retrieveCreatedFeature(createFeatureRequest).map(_.toCreateFeatureResponse).list.apply
      if(features.nonEmpty) features.head else throw new CosmicFeatureTurtleException
    } else
      throw new CosmicFeatureTurtleException
  }

  def deleteFeature(deleteFeatureRequest: DeleteFeatureRequest): DeleteFeatureResponse = {
    validateCredentialRequest(deleteFeatureRequest)
    val result = Queries.deleteFeature(deleteFeatureRequest).update.apply
    if(result == 1)
      DeleteFeatureResponse(s"FeatureSummary ${deleteFeatureRequest.idFeature} was deleted.")
    else
      throw new CosmicFeatureTurtleException("Something went wrong")
  }

  def editFeature(editFeatureRequest: EditFeatureRequest): EditFeatureResponse = {
    validateCredentialRequest(editFeatureRequest)
    val result = Queries.editFeature(editFeatureRequest).update.apply
    if(result == 1)
      EditFeatureResponse(s"FeatureSummary ${editFeatureRequest.idFeature} was updated.")
    else
      throw new CosmicFeatureTurtleException
  }

  def getFeatureSummaries(params: Map[String, String]): List[FeatureSummary] = {
    Queries.getFeatureSummaries(params.getOrElse("limit", "10").toInt, params.getOrElse("skip", "0").toInt).map(_.toFeatureSummary).list.apply
  }

  def getFeatureById(idFeature: Int): Feature = {
    val comments = Queries.getFeatureCommentsById(idFeature).map(_.toFeatureComment).list.apply
    val votes = Queries.getFeatureVotesById(idFeature).map(_.toFeatureVote).list.apply
    val features = Queries.getFeatureById(idFeature).map(_.toFeature(comments, votes)).list.apply
    if(features.nonEmpty) features.head else throw new CosmicFeatureTurtleException("I am become death")
  }

  def createVote(createVoteRequest: CreateVoteRequest): CreateVoteResponse = {
    validateCredentialRequest(createVoteRequest)
    val result = Queries.createVote(createVoteRequest).update.apply
    if(result == 1) {
      val votes = Queries.retrieveCreatedVote(createVoteRequest).map(_.toCreateVoteResponse).list.apply
      if(votes.nonEmpty) votes.head else throw new CosmicFeatureTurtleException
    } else
      throw new CosmicFeatureTurtleException
  }

  def deleteVote(deleteVoteRequest: DeleteVoteRequest): DeleteVoteResponse = {
    validateCredentialRequest(deleteVoteRequest)
    val result = Queries.deleteVote(deleteVoteRequest).update.apply
    if(result == 1)
      DeleteVoteResponse(s"Vote ${deleteVoteRequest.idVote} was deleted.")
    else
      throw new CosmicFeatureTurtleException
  }

  def editVote(editVoteRequest: EditVoteRequest): EditVoteResponse = {
    validateCredentialRequest(editVoteRequest)
    val result = Queries.editVote(editVoteRequest).update.apply
    if(result == 1)
      EditVoteResponse(s"Vote ${editVoteRequest.idVote} was updated.")
    else
      throw new CosmicFeatureTurtleException
  }

  def createComment(createCommentRequest: CreateCommentRequest): CreateCommentResponse = {
    validateCredentialRequest(createCommentRequest)
    val result = Queries.createComment(createCommentRequest).update.apply
    if(result == 1) {
      val comments = Queries.retrieveCreatedComment(createCommentRequest).map(_.toCreateCommentResponse).list.apply
      if(comments.nonEmpty) comments.head else throw new CosmicFeatureTurtleException
    } else
      throw new CosmicFeatureTurtleException
  }

  def deleteComment(deleteCommentRequest: DeleteCommentRequest): DeleteCommentResponse = {
    validateCredentialRequest(deleteCommentRequest)
    val result = Queries.deleteComment(deleteCommentRequest).update.apply
    if(result == 1)
      DeleteCommentResponse(s"Comment ${deleteCommentRequest.idComment} was deleted.")
    else
      throw new CosmicFeatureTurtleException
  }

  def editComment(editCommentRequest: EditCommentRequest): EditCommentResponse = {
    validateCredentialRequest(editCommentRequest)
    val result = Queries.editComment(editCommentRequest).update.apply
    if(result == 1)
      EditCommentResponse(s"Comment ${editCommentRequest.idComment} was updated.")
    else
      throw new CosmicFeatureTurtleException
  }
}

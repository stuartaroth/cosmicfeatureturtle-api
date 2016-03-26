package cosmicfeatureturtle.data

import scalikejdbc.{AutoSession, ConnectionPool}
import cosmicfeatureturtle.models.Models._
import cosmicfeatureturtle.models.ResultImplicits._

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
        throw new CosmicFeatureTurtleException("Something went wrong")
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
      if(features.nonEmpty) features.head else throw new CosmicFeatureTurtleException("Ehhhhh")
    } else
      throw new CosmicFeatureTurtleException("Something went wrong")
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
      throw new CosmicFeatureTurtleException("Something went wrong")
  }

  def getFeatureSummaries(params: Map[String, String]): List[FeatureSummary] = {
    Queries.getFeatureSummaries(params.getOrElse("limit", "10").toInt, params.getOrElse("skip", "0").toInt).map(_.toFeatureSummary).list.apply
  }

  def getFeature(idFeature: Int): Feature = {
    val comments = Queries.getFeatureCommentsById(idFeature).map(_.toFeatureComment).list.apply
    val votes = Queries.getFeatureVotesById(idFeature).map(_.toFeatureVote).list.apply
    val features = Queries.getFeatureById(idFeature).map(_.toFeature(comments, votes)).list.apply
    if(features.nonEmpty) features.head else throw new CosmicFeatureTurtleException("I am become death")
  }
}

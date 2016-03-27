package cosmicfeatureturtle.data

import cosmicfeatureturtle.models.Models.ErrorMessage
import cosmicfeatureturtle.models.Models._
import spray.httpx.marshalling.ToResponseMarshallable

object Access {
  import spray.httpx.SprayJsonSupport.sprayJsonMarshaller
  import cosmicfeatureturtle.models.ModelsProtocol._

  def createUser(createUserRequest: CreateUserRequest): ToResponseMarshallable = {
    try {
      Execute.createUser(createUserRequest)
    } catch {
      case e: com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException => ErrorMessage("The username already exists.")
      case e: CosmicFeatureTurtleException => ErrorMessage(e.message)
      case e: Exception => ErrorMessage("Something went exceptionally wrong.")
    }
  }

  def loginUser(loginUserRequest: LoginUserRequest): ToResponseMarshallable = {
    try {
      Execute.loginUser(loginUserRequest)
    } catch {
      case e: CosmicFeatureTurtleException => ErrorMessage(e.message)
      case e: Exception => ErrorMessage("Something went exceptionally wrong.")
    }
  }

  def createFeature(createFeatureRequest: CreateFeatureRequest): ToResponseMarshallable = {
    try {
      Execute.createFeature(createFeatureRequest)
    } catch {
      case e: com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException => ErrorMessage("The user already has a feature with that title.")
      case e: Exception => ErrorMessage("Something went exceptionally wrong.")
    }
  }

  def deleteFeature(deleteFeatureRequest: DeleteFeatureRequest): ToResponseMarshallable = {
    try {
      Execute.deleteFeature(deleteFeatureRequest)
    } catch {
      case e: Exception => ErrorMessage("Something went exceptionally wrong.")
    }
  }

  def editFeature(editFeatureRequest: EditFeatureRequest): ToResponseMarshallable = {
    try {
      Execute.editFeature(editFeatureRequest)
    } catch {
      case e: Exception => ErrorMessage("Something went exceptionally wrong.")
    }
  }

  def getFeatureSummaries(params: Map[String, String]): ToResponseMarshallable = {
    try {
      Execute.getFeatureSummaries(params)
    } catch {
      case e: Exception => ErrorMessage("Ehh")
    }
  }

  def getFeature(idFeature: Int) : ToResponseMarshallable = {
    try {
      Execute.getFeatureById(idFeature)
    } catch {
      case e: Exception => ErrorMessage("Ehh")
    }
  }

  def createVote(createVoteRequest: CreateVoteRequest): ToResponseMarshallable = {
    try {
      Execute.createVote(createVoteRequest)
    } catch {
      case e: Exception => ErrorMessage("Ehh")
    }
  }

  def deleteVote(deleteVoteRequest: DeleteVoteRequest): ToResponseMarshallable = {
    try {
      Execute.deleteVote(deleteVoteRequest)
    } catch {
      case e: Exception => ErrorMessage("Ehh")
    }
  }

  def editVote(editVoteRequest: EditVoteRequest): ToResponseMarshallable = {
    try {
      Execute.editVote(editVoteRequest)
    } catch {
      case e: Exception => ErrorMessage("Ehh")
    }
  }
}

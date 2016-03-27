package cosmicfeatureturtle.models

import cosmicfeatureturtle.models.Models._
import spray.json._

object ModelsProtocol extends DefaultJsonProtocol {
  implicit val userFormat = jsonFormat2(User)
  implicit val featureSummaryFormat = jsonFormat7(FeatureSummary)
  implicit val featureCommentFormat = jsonFormat3(FeatureComment)
  implicit val featureVoteFormat = jsonFormat3(FeatureVote)
  implicit val featureFormat = jsonFormat6(Feature)
  implicit val errorMessageFormat = jsonFormat1(ErrorMessage)
  implicit val createUserFormat = jsonFormat3(CreateUserRequest)
  implicit val createUserResponseFormat = jsonFormat3(CreateUserResponse)
  implicit val loginUserRequestFormat = jsonFormat2(LoginUserRequest)
  implicit val loginUserResponseFormat = jsonFormat3(LoginUserResponse)
  implicit val createFeatureRequestFormat = jsonFormat4(CreateFeatureRequest)
  implicit val createFeatureResponseFormat = jsonFormat3(CreateFeatureResponse)
  implicit val deleteFeatureRequestFormat = jsonFormat3(DeleteFeatureRequest)
  implicit val deleteFeatureResponseFormat = jsonFormat1(DeleteFeatureResponse)
  implicit val editFeatureRequestFormat = jsonFormat5(EditFeatureRequest)
  implicit val editFeatureResponseFormat = jsonFormat1(EditFeatureResponse)
  implicit val createVoteRequestFormat = jsonFormat4(CreateVoteRequest)
  implicit val createVoteResponseFormat = jsonFormat1(CreateVoteResponse)
  implicit val editVoteRequestFormat = jsonFormat4(EditVoteRequest)
  implicit val editVoteResponseFormat = jsonFormat1(EditVoteResponse)
  implicit val deleteVoteRequestFormat = jsonFormat3(DeleteVoteRequest)
  implicit val deleteVoteResponseFormat = jsonFormat1(DeleteVoteResponse)
}

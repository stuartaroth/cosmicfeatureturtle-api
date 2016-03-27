package cosmicfeatureturtle.routing

import akka.actor.Actor
import cosmicfeatureturtle.data.Access
import cosmicfeatureturtle.models.Models._
import spray.http.HttpHeaders.RawHeader
import spray.routing._

class MyServiceActor extends Actor with MyService {
  def actorRefFactory = context
  def receive = runRoute(myRoute)
}

trait MyService extends HttpService {
  import spray.httpx.SprayJsonSupport.sprayJsonUnmarshaller
  import cosmicfeatureturtle.models.ModelsProtocol._

  val myRoute =
    respondWithHeader(RawHeader("Access-Control-Allow-Origin", "*")) {
      pathPrefix("") {
        pathEndOrSingleSlash {
          get {
            complete("root")
          }
        }
      } ~ pathPrefix("user") {
        pathEndOrSingleSlash {
          post {
            complete("user")
          }
        } ~ pathPrefix("create") {
          pathEndOrSingleSlash {
            post {
              entity(as[CreateUserRequest]) { createUserRequest =>
                complete(Access.createUser(createUserRequest))
              }
            }
          }
        } ~ pathPrefix("login") {
          pathEndOrSingleSlash {
            post {
              entity(as[LoginUserRequest]) { loginUserRequest =>
                complete(Access.loginUser(loginUserRequest))
              }
            }
          }
        }
      } ~ pathPrefix("feature") {
        pathEndOrSingleSlash {
          get {
            parameterMap { params =>
              complete(Access.getFeatureSummaries(params))
            }
          }
        } ~ pathPrefix(IntNumber) { idFeature =>
          pathEndOrSingleSlash {
            get {
              complete(Access.getFeature(idFeature))
            }
          }
        } ~ pathPrefix("create") {
          pathEndOrSingleSlash {
            post {
              entity(as[CreateFeatureRequest]) { createFeatureRequest =>
                complete(Access.createFeature(createFeatureRequest))
              }
            }
          }
        } ~ pathPrefix("delete") {
          pathEndOrSingleSlash {
            post {
              entity(as[DeleteFeatureRequest]) { deleteFeatureRequest =>
                complete(Access.deleteFeature(deleteFeatureRequest))
              }
            }
          }
        } ~ pathPrefix("edit") {
          pathEndOrSingleSlash {
            post {
              entity(as[EditFeatureRequest]) { editFeatureRequest =>
                complete(Access.editFeature(editFeatureRequest))
              }
            }
          }
        }
      } ~ pathPrefix("vote") {
        pathEndOrSingleSlash {
          post {
            complete("VOTE")
          }
        }  ~ pathPrefix("create") {
          pathEndOrSingleSlash {
            post {
              entity(as[CreateVoteRequest]) { createVoteRequest =>
                complete(Access.createVote(createVoteRequest))
              }
            }
          }
        } ~ pathPrefix("delete") {
          pathEndOrSingleSlash {
            post {
              entity(as[DeleteVoteRequest]) { deleteVoteRequest =>
                complete(Access.deleteVote(deleteVoteRequest))
              }
            }
          }
        } ~ pathPrefix("edit") {
          pathEndOrSingleSlash {
            post {
              entity(as[EditVoteRequest]) { editVoteRequest =>
                complete(Access.editVote(editVoteRequest))
              }
            }
          }
        }
      }
    }
}

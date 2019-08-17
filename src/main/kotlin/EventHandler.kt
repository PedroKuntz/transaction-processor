import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.regions.Regions
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import com.amazonaws.services.dynamodbv2.document.DynamoDB
import com.amazonaws.services.dynamodbv2.document.Item
import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.amazonaws.services.lambda.runtime.events.SQSEvent

class EventHandler : RequestHandler<SQSEvent, String> {

    override fun handleRequest(input: SQSEvent?, context: Context?): String {
        if (context != null && input != null ) {
            val logger = context.logger
            logger.log("SQSEVENT: -> $input")
            val client = AmazonDynamoDBClientBuilder
                .standard()
                .withRegion(Regions.US_EAST_1)
                .withCredentials(
                    AWSStaticCredentialsProvider(
                            BasicAWSCredentials(, )
                ))
                .build()
            val dynamoDB = DynamoDB(client)
            val table = dynamoDB.getTable("transaction-event")

            val item = Item()
                .withPrimaryKey("transactionId", input.records.first().messageId)
                .withString("transaction", input.records.first().body)


// Write the item to the table
            val outcome = table.putItem(item)

        }
        return "TESTE"
    }
}
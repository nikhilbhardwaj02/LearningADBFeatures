// Databricks notebook source
val userName = dbutils.secrets.get(scope = "training-scope", key = "adbclientsecret")
print(userName)

// COMMAND ----------

spark.conf.set("fs.azure.account.auth.type.trngday3storageaccount.dfs.core.windows.net", "OAuth")
spark.conf.set("fs.azure.account.oauth.provider.type.trngday3storageaccount.dfs.core.windows.net", "org.apache.hadoop.fs.azurebfs.oauth2.ClientCredsTokenProvider")
spark.conf.set("fs.azure.account.oauth2.client.id.trngday3storageaccount.dfs.core.windows.net", "9aaa7b7b-e920-4893-a07d-23efd7e6a5eb")
spark.conf.set("fs.azure.account.oauth2.client.secret.trngday3storageaccount.dfs.core.windows.net", userName)
spark.conf.set("fs.azure.account.oauth2.client.endpoint.trngday3storageaccount.dfs.core.windows.net", "https://login.microsoftonline.com/72f988bf-86f1-41af-91ab-2d7cd011db47/oauth2/token")
spark.conf.set("fs.azure.createRemoteFileSystemDuringInitialization", "true")
dbutils.fs.ls("abfss://data@trngday3storageaccount.dfs.core.windows.net/")
spark.conf.set("fs.azure.createRemoteFileSystemDuringInitialization", "false")

// COMMAND ----------

// MAGIC %sh 
// MAGIC 
// MAGIC wget -P /tmp https://raw.githubusercontent.com/Azure/usql/master/Examples/Samples/Data/json/radiowebsite/small_radio_json.json

// COMMAND ----------

dbutils.fs.cp("file:///tmp/small_radio_json.json", "abfss://data@trngday3storageaccount.dfs.core.windows.net/")

// COMMAND ----------

// MAGIC %sql
// MAGIC SELECT * from radio_sample_data
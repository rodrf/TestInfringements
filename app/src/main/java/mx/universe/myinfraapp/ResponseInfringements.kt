package mx.universe.myinfraapp

import com.google.gson.annotations.SerializedName

data class ResponseInfringements(

	@field:SerializedName("ResponseInfringements")
	val responseInfringements: List<ResponseInfringementsItem?>? = null
)

data class ResponseInfringementsItem(

	@field:SerializedName("id_identifier_document")
	val idIdentifierDocument: Int? = null,

	@field:SerializedName("folio")
	val folio: String? = null,

	@field:SerializedName("date_infringement")
	val dateInfringement: String? = null,

	@field:SerializedName("infringement")
	val infringement: Int? = null,

	@field:SerializedName("num_doc")
	val numDoc: String? = null,

	@field:SerializedName("fraction")
	val fraction: Int? = null
)

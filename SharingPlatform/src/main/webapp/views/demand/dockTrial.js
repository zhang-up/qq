/**
 * 
 */

$(function() {
	
	addselect("T_DEMAND_OPERATE","OPERATE_RES_1","dealResult","",true,);
	addselect("T_DEMAND_OPERATE","CAUSE_1","backCause","",true,"");
	
	
	
});	
function findList(){
	$.ajax({
		url:'./tdemand/infoD/'+showDemandId,
		type:'post',
		dataType:'json',
	})
}
function detailApply(){
	
	$.ajax({
		url:'./tdemand/infoD/'+showDemandId,
		dataType:'json',
		type:'post',
		success: function(result){
			$('#proDepNameShow').val(result.provideDepName);
			$('#demandDepNameShow').val(result.demandDepName);
			$('#demandNameShow').val(result.demandName);
			$('#keyWordShow').val(result.keyWord);
			$('#demantDetailShow').val(result.demandDetail);
			$('#accessModeNameShow').val(result.accessModeName);
			$('#serveModeNameShow').val(result.serveModeName);
			$('#frequencyNameShow').val(result.frequencyName);
			$('#demandUseShow').val(result.demandUse);
		},
		error:commerror
	});
	
}


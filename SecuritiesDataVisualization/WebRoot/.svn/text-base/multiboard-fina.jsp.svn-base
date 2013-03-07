<%@ page language="java" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.7.1.custom.min.js"></script>
<link href="css/nv.d3.css" rel="stylesheet" type="text/css">
<link href="css/sexybuttons.css" rel="stylesheet" type="text/css">
<link href="css/dropdown-list.css" rel="stylesheet" type="text/css">
<script src="js/jquery.bgiframe.min.js" type="text/javascript"></script>
<script src="js/jquery.multiSelect.js" type="text/javascript"></script>
<link href="css/jquery.multiSelect.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">

	$(document).ready( function() {
		// Default options
		$("#industry").multiSelect({oneOrMoreSelected: '*'});
	});

</script>


<style>

body {
  overflow-y:scroll;
  margin: 0;
  padding: 0;
  background: #93cedf;
}

svg {
  overflow: hidden;
}

div {
  border: 0;
  margin: 0;
}

/*
#offsetDiv {
  margin-left: 100px;
  margin-top: 100px;
}
*/


#test1 {
  margin: 0;
}

#test1 svg {
  height: 500px;
}

</style>

<body>
  <div style="margin:5px 10px 20px 30px;height:50px;" align="left">
  	请选择行业：
		<select id="industry" name="industry[]" multiple="multiple" size="5">
			<option value=""></option>
			<option value="金融业" selected="selected">金融业</option>
			<option value="建筑业">建筑业</option>
			<option value="房地产业">房地产业</option>
			<option value="交通运输、仓储和邮政业">交通运输、仓储和邮政业</option>
			<option value="文化、体育和娱乐业">文化、体育和娱乐业</option>
			<option value="科学研究和技术服务业">科学研究和技术服务业</option>
			<option value="水利、环境和公共设施管理业">水利、环境和公共设施管理业</option>
			<option value="教育">教育</option>
			<option value="采矿业">采矿业</option>
			<option value="电力、热力、燃气及水生产和供应业">电力、热力、燃气及水生产和供应业</option>
			<option value="住宿和餐饮业">住宿和餐饮业</option>
			<option value="综合">综合</option>
			<option value="制造业">制造业</option>
			<option value="批发和零售业">批发和零售业</option>
			<option value="卫生和社会工作">卫生和社会工作</option>
			<option value="租赁和商务服务业">租赁和商务服务业</option>
			<option value="信息传输、软件和信息技术服务业">信息传输、软件和信息技术服务业</option>
			<option value="农、林、牧、渔业">农、林、牧、渔业</option>
		</select>

	&nbsp;&nbsp;&nbsp;&nbsp;年度：
	<div class="dropdown">
		<select name="one" class="dropdown-select" id="year">
			<option value="2007">2007</option>
			<option value="2008">2008</option>
			<option value="2009">2009</option>
			<option value="2010">2010</option>
			<option value="2011">2011</option>
			<option value="2012">2012</option>

		</select>
	</div>

	&nbsp;&nbsp;&nbsp;&nbsp;季度：
	<div class="dropdown">
		<select name="one" class="dropdown-select" id="report">
			<option value="1">一季报</option>
			<option value="2">半年报</option>
			<option value="3">三季报</option>
			<option value="4">年报</option>

		</select>
	</div>

  	<button class="sexybutton sexyorange" onclick="loaddata()"><span><span><span class="ok"> 提交</span></span></span></button>

  </div>


<div id="offsetDiv">
  <div id="test1" class='with-3d-shadow with-transitions'>
    <svg></svg>
  </div>
</div>

<script src="js/nvd3/d3.v3.js"></script>
<!--<script src="../lib/fisheye.js"></script>-->
<script src="js/nvd3/nv.d3.js"></script>
<script src="js/nvd3/tooltip.js"></script>
<script src="js/nvd3/utils.js"></script>
<script src="js/nvd3/legend.js"></script>
<script src="js/nvd3/axis.js"></script>
<script src="js/nvd3/distribution.js"></script>
<script src="js/nvd3/scatter.js"></script>
<script src="js/nvd3/scatterChart.js"></script>
<script>


var data;
//Format A
//window.onload=function loaddata(){
function loaddata(){
  $("#test1 svg").empty();
  /*var industry='建筑业,金融业,采矿业,农、林、牧、渔业,房地产业';*/
  var industry = $('#industry').attr("title");

  var year = $("#year").val();
  var report = $("#report").val();
  var monthday;
  if(report == '1'){
	monthday = '03-31';
  }else if(report=='2'){
  	monthday = '06-30';
  }else if(report=='3'){
  	monthday = '09-30';
  }else if(report=='4'){
  	monthday = '12-31';
  }
  var date = year+'-'+monthday;


 $.getJSON('displayBoardStocksFinaData.action?industry='+industry+'&date='+date , function(result) {
	data = eval(result);
	var chart;
	nv.addGraph(function() {
	  chart = nv.models.scatterChart()
	                .showDistX(true)
	                .showDistY(true)
	                .useVoronoi(true)
	                .color(d3.scale.category10().range())
	                .transitionDuration(300)
	                ;

	  chart.xAxis.tickFormat(d3.format('.02f'));
	  chart.yAxis.tickFormat(d3.format('.f'));
	  chart.tooltipContent(function(key) {
	      return '<h2>' + key + '</h2>';
	  });

	  d3.select('#test1 svg')
	      .datum(data)
	      .call(chart);

	  nv.utils.windowResize(chart.update);

	  chart.dispatch.on('stateChange', function(e) { ('New State:', JSON.stringify(e)); });

	  return chart;
	});


  });



}




/*function randomData(groups, points) { //# groups,# points per group
  var data = [],
      shapes = ['circle', 'cross', 'triangle-up', 'triangle-down', 'diamond', 'square'],
      random = d3.random.normal();

  for (i = 0; i < groups; i++) {
    data.push({
      key: 'Group ' + i,
      values: []
    });

    for (j = 0; j < points; j++) {
      data[i].values.push({
        x: random(),
        y: random(),
        size: Math.random()*15000,
        // shape: shapes[j % 6]
        shape: 'circle'
      });
    }
  }
  //var data = [
	//{
		//key: 'Group1',
		//values:[['x':3.4,'y':5.6,'size':150000,'shape':'circle'],['x':8.9,'y':6,'size':10000,'shape':'circle']]
	//}
   //];



  return data;
}*/



</script>

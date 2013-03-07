<%@ page language="java" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<meta charset="utf-8">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>treemap</title>
    <script type="text/javascript" src="js/d3.js"></script>
    <script type="text/javascript" src="js/d3.layout.min.js"></script>
    <script type="text/javascript" src="js/d3.geom.min.js"></script>
    <script type="text/javascript" src="js/raphael.min.js"></script>
    <script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="js/sea.js"></script>


  <STYLE type="text/css">
#chart {
    border-top: 1px dashed #F00;
    border-bottom: 1px dashed #F00;
    padding-left: 20px;
}

</STYLE>
  </head>

  <body>
    <div id="hover">hover node info</div>
    <div id="info">click leaf node info</div>
    <div id="change"><span style="cursor:pointer"> 切换</span> </div>
    <div id="chart"></div>
    <div id="correlation"></div>


    <script type="text/javascript">
    $(function(){
        $("#change").hide();
        $("#change").click(function(){
            if($("#change").data('id') == "chart") {
                $('#chart').hide();
                $('#correlation').show();
                $("#change").data('id',"correlation");
            } else {
                $('#correlation').hide();
                $('#chart').show();
                 $("#change").data('id',"chart");
            }
        });
    });
    </script>
    <script type="text/javascript">
    //http://planetozh.com/blog/2008/04/javascript-basename-and-dirname/
    var dir = window.location.href.replace(/\\/g,'/').replace(/\/[^\/]*$/, '');
    seajs.config({
        alias: {
            'datav': dir + '/js/datav.js',
            'treemap': dir + '/js/treemap.js',
            'force': dir + '/js/force.js',
        }
    });
    </script>
    <script type="text/javascript">

    seajs.use(["treemap","force", "datav"], function (Treemap, Force, DataV) {
        DataV.changeTheme("theme0");
        var treemap = new Treemap("chart", {});
        d3.json("hello.json", function(source) {
            treemap.setSource(source);
            treemap.setLeafNodeClick(function (e){
                //$("info").html(this.name);
                //document.getElementById("info").innerHTML = this.name + " is a leaf node.";
                var year = this.name;
                var industry = this.parent.name;
                $.getJSON('displayCorrelation.action?year='+year+'&industry='+industry,
                   function(result) {
                   $('#correlation').empty();
                    var data = eval(result);
                     var net = new Force("correlation", {
                        width: 1200,
                        height: 700,
                        tag: true
                    });
                    net.setSource(data);
                    net.render();
                    $('#chart').hide();
                    $('#change').show();
                    $('#correlation').show();
                    $('#change').data("id","correlation");
                });});
            treemap.hover(function(){
                    document.getElementById("hover").innerHTML = "hover " + this.name;
                },function(){
                    document.getElementById("hover").innerHTML = "leave " + this.name;
                });
            treemap.render();
        });
    });
    </script>

  </body>
</html>
$(document).ready(function(){		
	'use strict';
	var root = "http://localhost:8080/demos";

	//Todo 토큰 관련 정의가 필요
	// var annotator =$("#annotator").annotator()
     //    .annotator('setupPlugins', {}, {
     //        // Disable the tags plugin
     //        Tags: true,
     //        // Filter plugin options
     //        Filter: {
     //            addAnnotationFilter: true, // Turn off default annotation filter
     //            filters: [{label: 'Quote', property: 'quote'}] // Add a quote filter
     //        },
	// 		Auth: {
     //        	tokenUrl: '/auth/token'
	// 		}
     //    });


	(function initPdfView(){
		var iframe_url = root+"/resources/pdfjs/web/viewer.html?file=/demos/pdf-down";
		var dom = "<iframe src='"+iframe_url+"' style='height:100%; width:100%'></iframe>";
		$("#pdf-viewer").html(dom);


		//01-25 PDFJS로의 force가 작동하지 않음
		// var options = {
         //    pdfOpenParams: {
         //        navpanes: 0,
         //        toolbar: 0,
         //        statusbar: 0,
         //        view: "FitV",
         //        pagemode: "thumbs",
         //        page: 2
         //    },
		//     forcePDFJS: false,
		//     PDFJS_URL: "http://localhost:8080/SpringTest/resources/pdfjs/web/viewer.html"
		// };
		
		//01-25 PDFJS로의 force가 작동하지 않음
//        var myPDF 			  = PDFObject.embed(root+"/pdf-down",
//            "#example1",
//            options);
//        var el = document.querySelector("#results");
//        el.setAttribute("class", (myPDF) ? "success" : "fail");
//        el.innerHTML = (myPDF) ? "PDFObject was successful!" : "Uh-oh, the embed didn't work.";


        //01-25 PDFJS로의 force가 작동하지 않음
		// if(PDFObject.supportsPDFs){
		//    console.log("This browser supports inline PDFs.");
		//    var html = '';
		//    	   html+= ' <embed src="/SpringTest/pdf-down" class="width-100" '
		// 	   html+= ' height="800"'
		// 	   html+= ' type="application/pdf"> ';
		//     console.log(html);
		//    	$("#example1").html(html);
		// } else {
		// 	console.log("DEBUG CHECK ELSE CONTROL")
		// 	options['forcePDFJS'] = true;
		// 	options['PDFJS_URL']  = root+"/resources/pdfjs/web/viewer.html";
		// 	var myPDF 			  = PDFObject.embed(root+"/pdf-down",
		// 											"#example1",
		// 											options);
		// }
	})();
	
	//Todo
	//apiDoc 설정
	
	(function initFancytree(){
		$("#fancyTree").fancytree({
			checkbox: true, 
			selectMode: 2,
		 	extensions: ["filter", "childcounter"],
		 	quicksearch: true,
		 	childcounter: {
		 		deep: true,
		 		hideZeros: true,
		 		hideExpanded: true
	        },
		 	select: function(event, data) {
		        // Display list of selected nodes
		        var selNodes = data.tree.getSelectedNodes();
		        // convert to title/key array
		        var selKeys = $.map(selNodes, function(node){
		             return "[" + node.key + "]: '" + node.title + "'";
		          });
		        $("#echoSelection2").text(selKeys.join(", "));
		      },
		      click: function(event, data) {
		        // We should not toggle, if target was "checkbox", because this
		        // would result in double-toggle (i.e. no toggle)
		        if( $.ui.fancytree.getEventTargetType(event) === "title" ){
		          data.node.toggleSelected();
		        }
		      },
		      keydown: function(event, data) {
		        if( event.which === 32 ) {
		          data.node.toggleSelected();
		          return false;
		        }
		      },
		      // The following options are only required, if we have more than one tree on one page:
		    cookieId: "fancytree-Cb2",
		    idPrefix: "fancytree-Cb2-",
		    	  
		 	source: {
		 	   url: root+'/binders',
		 	   cache: false
	 		},			
		    filter: {
		           autoApply: true,   // Re-apply last filter if lazy data is loaded
		           autoExpand: false, // Expand all branches that contain matches while filtered
		           counter: true,     // Show a badge with number of matching child nodes near parent icons
		           fuzzy: false,      // Match single characters in order, e.g. 'fb' will match 'FooBar'
		           hideExpandedCounter: true,  // Hide counter badge if parent is expanded
		           hideExpanders: false,       // Hide expanders if all child nodes are hidden by filter
		           highlight: true,   // Highlight matches by wrapping inside <mark> tags
		           leavesOnly: false, // Match end nodes only
		           nodata: true,      // Display a 'no data' status node if result is empty
		           mode: "dimm"       // Grayout unmatched nodes (pass "hide" to remove unmatched node instead)
	         },
		});
	})();
	
	$("input[name=search]").keyup(function(e){
	      var n,
	        tree = $.ui.fancytree.getTree(),
	        args = "autoApply autoExpand fuzzy hideExpanders highlight leavesOnly nodata".split(" "),
	        opts = {},
	        filterFunc = $("#branchMode").is(":checked") ? tree.filterBranches : tree.filterNodes,
	        match = $(this).val();

	      $.each(args, function(i, o) {
	        opts[o] = $("#" + o).is(":checked");
	      });
	      //opts.mode = $("#hideMode").is(":checked") ? "hide" : "dimm";
	      opts.mode = "hide";

	      if(e && e.which === $.ui.keyCode.ESCAPE || $.trim(match) === ""){
	        $("button#btnResetSearch").click();
	        $("span#matches").text("");
	        tree.clearFilter();
	        return;
	      }
	      
	      // Pass a string to perform case insensitive matching
	      n = filterFunc.call(tree, match, opts);
	      
	      $("button#btnResetSearch").attr("disabled", false);
	      $("span#matches").text("(" + n + " matches)");
	    }).focus();

	
	
	(function initZTree(){
		var setting = {
			view:{
//				addHoverDom : addHoverDom,
//				removeHoverDom: removeHoverDom,
				selectedMulti : false
			},
			edit: {
				enable: true,
				showRemoveBtn: true,
				showRenameBtn: true,
				drag:{
					isCopy : true,
					isMove : true,
					prev : true,
					inner : false,
					next : true
				}
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
//				beforeDrag: beforeDrag,
//				beforeDrop: beforeDrop, 
//				beforeRename : beforeRename,
//				beforeRemove : beforeRemove
			}
		};
		
		$.ajax({
			type: "GET",
			url : root+"/spec/tree",
			success: function(res){
				console.log("DEBUG CHECK RES:", res);
				

				var zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, res);
			}
		});
		
//		var zNodes = [
//	              {name:"test1", open:true, children:[
//	                 {name:"test1_1"}, {name:"test1_2"}]},
//	              {name:"test2", open:true, children:[
//	                 {name:"test2_1"}, {name:"test2_2"}]}
//	              ];
//
//		var zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
	})();
	/*
	 * 
	 * var clickHandler = function (e){

			e.preventDefault();
		
			var pdfURL = this.getAttribute("href");
		
			var options = {
				pdfOpenParams: {
					navpanes: 0,
					toolbar: 0,
					statusbar: 0,
					view: "FitV"
				}
			};
		
			var myPDF = PDFObject.embed(pdfURL, "#pdf", options);
		
			var el = document.querySelector("#results");
			el.setAttribute("class", (myPDF) ? "success" : "fail");
			el.innerHTML = (myPDF) ? "PDFObject successfully embedded '" + pdfURL + "'!" : "Uh-oh, the embed didn't work.";
		
		};
		
		var a = document.querySelectorAll(".embed-link");
		
		for(var i=0; i < a.length; i++){
			a[i].addEventListener("click", clickHandler);
		}
	 * 
	 */ 
});
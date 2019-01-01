
$(function(){
	$('#tt').tree({
		url:'/admin/getAllNavs.do',
		onClick:function(node){
			if(node.href==null||node.href==undefined){
				$('#tt').tree(node.state === 'closed' ? 'expand' : 'collapse', node.target);  
//				if(node.state=="closed"){
//					node.state="open";
//					$('#tt').tree('expand',node.target);
//				}
//				else{
//					node.state="closed";
//					$('#tt').tree('collapse',node.target);
//				}
			}
			else{
				if($("#tab").tabs('exists',node.text)){
					$("#tab").tabs('select',node.text);
				}
				else{
					$("#tab").tabs('add',{
						title:node.text,
						iconCls:node.iconCls, 
						content:'Tab Body',    
						closable:true,  
						href:node.href
					});
				}
				
			}
			
		}
	})
});
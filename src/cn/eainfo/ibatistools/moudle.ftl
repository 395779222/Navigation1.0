<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE sqlMap
PUBLIC "-//iBATIS.com//DTD SQL Map 2.0//EN"
"http://www.ibatis.com/dtd/sql-map-2.dtd">
<sqlMap namespace="${beanName}">
	<typeAlias alias="${beanName?uncap_first}" type="${moudle}" />
	<typeAlias alias="${beanName?uncap_first}Model"
		type="${beanName}Model" />
	<select id="getBean" parameterClass="String"
		resultClass="${beanName?uncap_first}">
		SELECT * from ${beanName?uncap_first} where ${beanId}=#${beanId}#
	</select>
	 <select id="get${beanName}Count" resultClass="long"
		parameterClass="${beanName?uncap_first}Model">
		SELECT count(*) from ${beanName?uncap_first}  
 </select>
	<select id="get${beanName}Record" parameterClass="${beanName?uncap_first}Model"
		resultClass="${beanName?uncap_first}">
		select * FROM ( select Table_A.*,rownum as RN FROM ( SELECT *
		from ${beanName?uncap_first}  
		<![CDATA[) Table_A WHERE ROWNUM <= #end# ]]>
		) Table_B
		<![CDATA[ WHERE  RN > #start#  ]]>
	</select>
	<insert id="insert" parameterClass="${beanName?uncap_first}">
		insert into ${beanName?uncap_first}
		  (<#list fields as item><#if popLength=item_index>${item.name}<#else>${item.name},</#if></#list>)
		values
		(<#list fields as item><#if popLength=item_index>#${item.name}#<#else>#${item.name}#,</#if></#list>)
	</insert>
	<update id="update" parameterClass="${beanName?uncap_first}">
		update ${beanName?uncap_first} set
	  <#list fields as item><#if popLength=item_index>${item.name}=#${item.name}#<#else><#if item.key=='key'><#else>${item.name}=#${item.name}#,</#if></#if></#list>
		where  ${beanId}=#${beanId}#
	</update>
	 
</sqlMap>

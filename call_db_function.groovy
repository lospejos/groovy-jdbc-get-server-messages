#!/usr/bin/env groovy

@GrabConfig(systemClassLoader=true)
@Grab('org.postgresql:postgresql:+')

import groovy.sql.Sql

Class.forName('org.postgresql.Driver')

def sql = Sql.newInstance('jdbc:postgresql://localhost/postgres', 'postgres', 'postgres')
def rows = sql.rows([param: 'Param_Value'], 'select * from testme(param => :param)')
// What to do here to get output: "Param value is: Hello" from function "raise notice" statement?
println rows

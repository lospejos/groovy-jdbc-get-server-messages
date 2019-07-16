create or replace function testme (
    param varchar,
    raise_error boolean default false
)
returns varchar
as
$$
declare
begin
    raise debug 'Debug message. Param value is: %', param;
    raise notice 'Notice message. Param value is: %', param;
    raise warning 'Warning message. Param value is: %', param;

    if raise_error then
        raise exception 'Error from function, param was: %', param using hint='To disable error, set raise_error=false or do not set this parameter at all', errcode = 'L1XE1';
    end if;

    return concat('Response on ', param);
end
$$ language plpgsql;


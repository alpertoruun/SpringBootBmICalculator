
CREATE MATERIALIZED VIEW avgView AS
select avg(index_result) as ortalama from my_user;

CREATE OR REPLACE FUNCTION get_avgview_data()
    RETURNS TABLE (avg_value double precision)
AS $$
BEGIN
    RETURN QUERY SELECT ortalama FROM avgview;
END;
$$
    LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION refresh_avgview()
    RETURNS TRIGGER AS
$$
BEGIN
    REFRESH MATERIALIZED VIEW avgView;
    RETURN NULL;
END;
$$
    LANGUAGE plpgsql;



CREATE or replace TRIGGER trig_refresh_avgview
    AFTER INSERT OR UPDATE OR DELETE ON my_user
    FOR EACH STATEMENT
EXECUTE FUNCTION refresh_avgview();



SELECT * FROM get_avgview_data();

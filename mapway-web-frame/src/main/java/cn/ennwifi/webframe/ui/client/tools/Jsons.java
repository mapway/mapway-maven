package cn.ennwifi.webframe.ui.client.tools;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONNull;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

/**
 * The type Jsons.
 *
 * @author zhangjianshe @navinfo.com
 */
public class Jsons {

    /**
     * 获取对象中的值
     *
     * @param json JSON对象 obj
     * @param path JSON对象图 name.surname
     * @param dv   缺省值
     * @return as int
     */
    public final static Integer getAsInt(JSONObject json, String path, Integer dv) {
    JSONObject obj = json;

    JSONNumber value = null;
    if (path != null && path.length() > 0) {
      String[] paths = path.split("\\.");
      for (int i = 0; i < paths.length; i++) {
        String p = paths[i];
        if (obj == null)
          return dv;
        JSONValue v = obj.get(p);
        if (v == null) {
          return dv;
        }
        if (i < paths.length - 1) {
          obj = v.isObject();
        } else {
          value = v.isNumber();
        }
      }
    }

    if (value != null) {
      return (int) value.doubleValue();
    } else {
      return dv;
    }
  }

    /**
     * 获取对象中的值
     *
     * @param json JSON对象 obj
     * @param path JSON对象图 name.surname
     * @param dv   缺省值
     * @return as string
     */
    public final static String getAsString(JSONObject json, String path, String dv) {
    JSONObject obj = json;
    JSONValue value = null;
    if (path != null && path.length() > 0) {
      String[] paths = path.split("\\.");
      for (int i = 0; i < paths.length; i++) {
        String p = paths[i];
        if (obj == null)
          return dv;
        JSONValue v = obj.get(p);
        if (v == null) {
          return dv;
        }
        if (i < paths.length - 1) {
          obj = v.isObject();
        } else {
          value = v;
        }
      }
    }

    if (value != null) {
      return toObjString(value, dv);
    } else {
      return dv;
    }
  }

  private static String toObjString(JSONValue v, String dv) {

    if (v == null) {
      return dv;
    }

    JSONNull n = v.isNull();
    if (n != null) {
      return "";
    }

    JSONString str = v.isString();
    if (str != null) {
      return str.stringValue();
    }

    JSONBoolean b = v.isBoolean();
    if (b != null) {
      return b.toString();
    }

    JSONNumber num = v.isNumber();
    if (num != null) {
      return num.toString();
    }

    JSONObject obj = v.isObject();
    if (obj != null) {
      return obj.toString();
    }

    return "";

  }

    /**
     * Gets as array.
     *
     * @param json the json
     * @param path the path
     * @param dv   the dv
     * @return as array
     */
    public static JSONArray getAsArray(JSONObject json, String path, JSONArray dv) {
    JSONObject obj = json;
    JSONArray value = null;
    if (path != null && path.length() > 0) {
      String[] paths = path.split("\\.");
      for (int i = 0; i < paths.length; i++) {
        String p = paths[i];
        if (obj == null)
          return dv;
        JSONValue v = obj.get(p);
        if (v == null) {
          return dv;
        }
        if (i < paths.length - 1) {
          obj = v.isObject();
        } else {
          value = v.isArray();
        }
      }
    }

    if (value != null) {
      return value.isArray();
    } else {
      return dv;
    }
  }

    /**
     * Gets as boolean.
     *
     * @param json the json
     * @param path the path
     * @param dv   the dv
     * @return as boolean
     */
    public static boolean getAsBoolean(JSONObject json, String path, boolean dv) {
    JSONObject obj = json;
    JSONBoolean value = null;
    if (path != null && path.length() > 0) {
      String[] paths = path.split("\\.");
      for (int i = 0; i < paths.length; i++) {
        String p = paths[i];
        if (obj == null)
          return dv;
        JSONValue v = obj.get(p);
        if (v == null) {
          return dv;
        }
        if (i < paths.length - 1) {
          obj = v.isObject();
        } else {
          value = v.isBoolean();
        }
      }
    }

    if (value != null) {
      return value.booleanValue();
    } else {
      return dv;
    }
  }

    /**
     * Gets as double.
     *
     * @param json the json
     * @param path the path
     * @param dv   the dv
     * @return as double
     */
    public static Double getAsDouble(JSONObject json, String path, double dv) {
    JSONObject obj = json;
    JSONNumber value = null;
    if (path != null && path.length() > 0) {
      String[] paths = path.split("\\.");
      for (int i = 0; i < paths.length; i++) {
        String p = paths[i];
        if (obj == null)
          return dv;
        JSONValue v = obj.get(p);
        if (v == null) {
          return dv;
        }
        if (i < paths.length - 1) {
          obj = v.isObject();
        } else {
          value = v.isNumber();
        }
      }
    }

    if (value != null) {
      return value.doubleValue();
    } else {
      return dv;
    }
  }

    /**
     * Gets as object.
     *
     * @param json the json
     * @param path the path
     * @param dv   the dv
     * @return as object
     */
    public static JSONObject getAsObject(JSONObject json, String path, JSONObject dv) {
    JSONObject obj = json;
    if (path != null && path.length() > 0) {
      String[] paths = path.split("\\.");
      for (int i = 0; i < paths.length; i++) {
        String p = paths[i];
        if (obj == null)
          return dv;
        JSONValue v = obj.get(p);
        if (v == null) {
          return dv;
        }
        if (i < paths.length - 1) {
          obj = v.isObject();
        }

      }
    }

    JSONObject n = obj.isObject();
    if (n != null) {
      return n;
    } else {
      return dv;
    }
  }

    /**
     * Gets as value.
     *
     * @param json the json
     * @param path the path
     * @param dv   the dv
     * @return as value
     */
    public static JSONValue getAsValue(JSONObject json, String path, JSONValue dv) {
    JSONObject obj = json;
    JSONValue value = null;
    if (path != null && path.length() > 0) {
      String[] paths = path.split("\\.");
      for (int i = 0; i < paths.length; i++) {
        String p = paths[i];
        if (obj == null)
          return dv;
        JSONValue v = obj.get(p);
        if (v == null) {
          return dv;
        }
        if (i < paths.length - 1) {
          obj = v.isObject();
        } else {
          value = v;
        }

      }
    }
    if (value != null) {
      return value;
    } else {
      return dv;
    }
  }

    /**
     * Log.
     *
     * @param log the log
     */
    public native static void log(String log)/*-{
		console.log(log);
  }-*/;

    /**
     * Log.
     *
     * @param log the log
     */
    public native static void log(JavaScriptObject log)/*-{
		console.log(log);
  }-*/;

    /**
     * To json string.
     *
     * @param o       the o
     * @param ifields 包含的字段
     * @param efields 排除的字段
     * @return string
     */
    public final static native String toJson(JavaScriptObject o, String ifields, String efields)/*-{

		var quoteString = function(str) {
			if (str.match(escape)) {
				return '"'
						+ str.replace(escape, function(a) {
							var c = meta[a];
							if (typeof c === 'string') {
								return c;
							}
							c = a.charCodeAt();
							return '\\u00' + Math.floor(c / 16).toString(16)
									+ (c % 16).toString(16);
						}) + '"';
			}
			return '"' + str + '"';
		};

		var escape = /["\\\x00-\x1f\x7f-\x9f]/g, meta = {
			'\b' : '\\b',
			'\t' : '\\t',
			'\n' : '\\n',
			'\f' : '\\f',
			'\r' : '\\r',
			'"' : '\\"',
			'\\' : '\\\\'
		}, hasOwn = Object.prototype.hasOwnProperty;

		var toJSON = function(o) {

			if (o === null) {
				return 'null';
			}

			var pairs, k, name, val, type = typeof o;

			if (type === 'undefined') {
				return undefined;
			}

			if (type === 'number' || type === 'boolean') {
				return String(o);
			}
			if (type === 'string') {
				return quoteString(o);
			}

			if (type === 'date') {
				var month = o.getUTCMonth() + 1, day = o.getUTCDate(), year = o
						.getUTCFullYear(), hours = o.getUTCHours(), minutes = o
						.getUTCMinutes(), seconds = o.getUTCSeconds(), milli = o
						.getUTCMilliseconds();

				if (month < 10) {
					month = '0' + month;
				}
				if (day < 10) {
					day = '0' + day;
				}
				if (hours < 10) {
					hours = '0' + hours;
				}
				if (minutes < 10) {
					minutes = '0' + minutes;
				}
				if (seconds < 10) {
					seconds = '0' + seconds;
				}
				if (milli < 100) {
					milli = '0' + milli;
				}
				if (milli < 10) {
					milli = '0' + milli;
				}
				return '"' + year + '-' + month + '-' + day + 'T' + hours + ':'
						+ minutes + ':' + seconds + '.' + milli + 'Z"';
			}

			pairs = [];

			if (typeof o === "array") {
				for (k = 0; k < o.length; k++) {
					pairs.push(toJSON(o[k]) || 'null');
				}
				return '[' + pairs.join(',') + ']';
			}

			if (typeof o === 'object') {
				var ireg = null;
				var ereg = null;

				if (efields != null && efields.length > 0) {
					ereg = eval("/" + efields + "/g");
				}
				if (ifields != null && ifields.length > 0) {
					ireg = eval("/" + ifields + "/g");
				}
				for (k in o) {
					if (hasOwn.call(o, k)) {
						type = typeof k;
						var ok = k;
						if (type === 'number') {
							name = '"' + k + '"';
						} else if (type === 'string') {
							name = quoteString(k);
						} else {
							continue;
						}
						type = typeof o[k];
						if (ereg != null) {
							if (ereg.test(ok)) {
								continue;
							}
						}
						if (ireg != null) {
							if (!ireg.test(ok)) {
								continue;
							}
						}
						if (type !== 'function' && type !== 'undefined') {
							val = toJSON(o[k]);
							pairs.push(name + ':' + val);
						}
					}
				}
				return '{' + pairs.join(',') + '}';
			}
		};
		return toJSON(o);
  }-*/;

}

/**
 * KVPairValue.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.genesyslab.www.interaction;

public class KVPairValue  implements java.io.Serializable {
    private java.lang.Integer valueInt;

    private java.lang.String valueString;

    private com.genesyslab.www.interaction.KVPair[] valueList;

    public KVPairValue() {
    }

    public KVPairValue(
           java.lang.Integer valueInt,
           java.lang.String valueString,
           com.genesyslab.www.interaction.KVPair[] valueList) {
           this.valueInt = valueInt;
           this.valueString = valueString;
           this.valueList = valueList;
    }


    /**
     * Gets the valueInt value for this KVPairValue.
     * 
     * @return valueInt
     */
    public java.lang.Integer getValueInt() {
        return valueInt;
    }


    /**
     * Sets the valueInt value for this KVPairValue.
     * 
     * @param valueInt
     */
    public void setValueInt(java.lang.Integer valueInt) {
        this.valueInt = valueInt;
    }


    /**
     * Gets the valueString value for this KVPairValue.
     * 
     * @return valueString
     */
    public java.lang.String getValueString() {
        return valueString;
    }


    /**
     * Sets the valueString value for this KVPairValue.
     * 
     * @param valueString
     */
    public void setValueString(java.lang.String valueString) {
        this.valueString = valueString;
    }


    /**
     * Gets the valueList value for this KVPairValue.
     * 
     * @return valueList
     */
    public com.genesyslab.www.interaction.KVPair[] getValueList() {
        return valueList;
    }


    /**
     * Sets the valueList value for this KVPairValue.
     * 
     * @param valueList
     */
    public void setValueList(com.genesyslab.www.interaction.KVPair[] valueList) {
        this.valueList = valueList;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof KVPairValue)) return false;
        KVPairValue other = (KVPairValue) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.valueInt==null && other.getValueInt()==null) || 
             (this.valueInt!=null &&
              this.valueInt.equals(other.getValueInt()))) &&
            ((this.valueString==null && other.getValueString()==null) || 
             (this.valueString!=null &&
              this.valueString.equals(other.getValueString()))) &&
            ((this.valueList==null && other.getValueList()==null) || 
             (this.valueList!=null &&
              java.util.Arrays.equals(this.valueList, other.getValueList())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getValueInt() != null) {
            _hashCode += getValueInt().hashCode();
        }
        if (getValueString() != null) {
            _hashCode += getValueString().hashCode();
        }
        if (getValueList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getValueList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getValueList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(KVPairValue.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "KVPairValue"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valueInt");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "ValueInt"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valueString");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "ValueString"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valueList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "ValueList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "KVPair"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.genesyslab.com/interaction", "kvitem"));
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}

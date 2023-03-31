import { useField } from "formik";
import { FC } from "react";
import ReactDatePicker from "react-datepicker";

interface Props {
    name: string;
}
export const FormikDatePickerField:FC<Props> = ({name}) => {
    const [field, meta, helpers] = useField(name);

    const { value } = meta;
    const { setValue } = helpers;
  
    return (
      <ReactDatePicker
        {...field}
        selected={value}
        onChange={(date) => setValue(date)}
      />
    );
}